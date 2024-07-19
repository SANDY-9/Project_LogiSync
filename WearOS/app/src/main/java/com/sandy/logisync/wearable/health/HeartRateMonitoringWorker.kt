package com.sandy.logisync.wearable.health

import android.content.Context
import android.os.PowerManager
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.sandy.logisync.data.health.HealthMeasureRepository
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.model.MeasuredAvailability
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime

/**
 * HeartRateMonitoringWorker 심박수 모니터링 워커
 * 1) 하루 근무시간에만 수집한다. 아침 8시를 첫 모니터링, 오후 7시를 마지막 모니터링으로
 * 2) 주말에는 모니터링하지 않는다.
 * 3) 모니터링 시간은 30분마다 한다.
 */
@HiltWorker
class HeartRateMonitoringWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val healthMeasureRepository: HealthMeasureRepository,
    private val networkRepository: NetworkRepository,
    private val powerManager: PowerManager,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.e("확인", "doWork: 워커 실행 ${LocalDateTime.now()}")
        monitorHeartRate()
        registerNextMonitoring()
        return Result.success()
    }

    private fun monitorHeartRate() {
        suspend {
            val wakeLock = powerManager.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK or
                        PowerManager.ACQUIRE_CAUSES_WAKEUP or
                        PowerManager.ON_AFTER_RELEASE,
                "LogiSync::WAKELOCK"
            )
            wakeLock.acquire(1 * 60 * 1000L /*1 minutes*/)
            healthMeasureRepository.getMeasuredHeartRate().onEach {
                // num++
                if (it.availability == MeasuredAvailability.UNAVAILABLE_DEVICE_OFF_BODY ||
                    it.availability == MeasuredAvailability.UNAVAILABLE
                ) awaitCancellation()
                Log.e("확인", "monitorHeartRate: 이거1 ${it.availability}")
            }.filter {
                it.availability == MeasuredAvailability.AVAILABLE
            }.collect {
                val heartRate = it.heartRate
                heartRate?.let { heartRate ->
                    Log.e("확인", "monitorHeartRate: 이거2 $heartRate")
                    wakeLock.release()
                    networkRepository.updateHeartRate(heartRate.bpm, heartRate.time)
                        .catch { error ->
                            Log.e("확인", "monitorHeartRate: $error")
                        }.collect()
                }
            }
        }
    }

    private fun registerNextMonitoring() {
        registerWorker(context)
    }

    companion object {
        private const val WORK_NAME = "heart_rate_monitoring_worker"
        fun registerWorker(
            context: Context,
        ) {
            val delay = getNextMonitoringDelay()
            Log.e("확인", "registerWorker: $delay")
            val workRequest = OneTimeWorkRequestBuilder<HeartRateMonitoringWorker>()
                .setInitialDelay(delay)
                .build()
            WorkManager.getInstance(context).enqueueUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
        }

        private fun getNextMonitoringDelay(): Duration {
            val nextMonitoringTime = getNextMonitoringTime()
            val currentTime = LocalDateTime.now()
            //val nextMonitoringTime = currentTime.plusMinutes(1)
            val delay = Duration.between(currentTime, nextMonitoringTime)
            Log.e("확인", "getNextMonitoringDelay: $nextMonitoringTime")
            return delay
        }

        /**
         * 심박수 모니터링 시간 계산
         * 1. 30분 간격으로 심박수 모니터링 한다.
         * 2. 근무 시간에만 모니터링해야 한다. -> 심박수 모니터링은 아침 8시 - 저녁 7시까지로 정한다.
         * 3. 주말(토요일, 일요일)에는 모니터링하지 않는다.
         * @param intervalMinutes 심박수 모니터링 간격(default = 30분)
         */
        private fun getNextMonitoringTime(intervalMinutes: Int = 30): LocalDateTime {
            val currentTime = LocalDateTime.now()

            // 모니터링 30분 기준 계산
            var nextMonitoringTime = if (currentTime.minute > intervalMinutes) {
                LocalDateTime.of(
                    currentTime.year,
                    currentTime.month,
                    if(currentTime.hour + 1 == 24) currentTime.dayOfMonth + 1 else currentTime.dayOfMonth,
                    if(currentTime.hour + 1 == 24) 0 else currentTime.hour + 1,
                    0
                )
            }
            else {
                LocalDateTime.of(
                    currentTime.year,
                    currentTime.month,
                    currentTime.dayOfMonth,
                    currentTime.hour,
                    intervalMinutes
                )
            }

            // 다음 모니터링 시간이 금요일 7시 이후~일요일 사이인 경우 모니터링은 다음주 월요일 8시
            nextMonitoringTime = when (nextMonitoringTime.dayOfWeek) {
                DayOfWeek.SATURDAY -> nextMonitoringTime.plusDays(2).withHour(8).withMinute(0)
                DayOfWeek.SUNDAY -> nextMonitoringTime.plusDays(1).withHour(8).withMinute(0)
                else -> nextMonitoringTime
            }

            // 다음 모니터링 시간이 오전 8시 이전인 경우 다음 모니터링은 아침 8시
            if (nextMonitoringTime.hour < 8) {
                nextMonitoringTime = LocalDateTime.of(
                    currentTime.year,
                    currentTime.month,
                    currentTime.dayOfMonth,
                    8,
                    0
                )
            }

            // 다음 모니터링 시간이 오후 7시 이후인 경우 다음 모니터링은 다음날 아침 8시
            if (nextMonitoringTime.hour >= 19) {
                nextMonitoringTime = LocalDateTime.of(
                    currentTime.year,
                    currentTime.month,
                    currentTime.dayOfMonth + 1,
                    8,
                    0
                )
            }

            return nextMonitoringTime
        }
    }
}
