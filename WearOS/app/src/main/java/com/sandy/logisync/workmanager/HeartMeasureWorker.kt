package com.sandy.logisync.workmanager

import android.content.Context
import android.os.PowerManager
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.domain.MeasureHeatRateUseCase
import com.sandy.logisync.model.MeasuredAvailability
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.timeout
import kotlin.time.Duration.Companion.seconds

@HiltWorker
class HeartRateMeasureWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val wearableDataStoreRepository: WearableDataStoreRepository,
    private val measureHeatRateUseCase: MeasureHeatRateUseCase,
) : CoroutineWorker(context, params) {

    private val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    private val wakeLock = powerManager.newWakeLock(
        PowerManager.SCREEN_BRIGHT_WAKE_LOCK or
                PowerManager.ACQUIRE_CAUSES_WAKEUP or
                PowerManager.ON_AFTER_RELEASE,
        "LogiSync::WAKELOCK"
    )

    override suspend fun doWork(): Result {
        measureHeartRate()
        return Result.success()
    }

    private suspend fun measureHeartRate() {
        wakeLock.acquire(1 * 60 * 1000L /*1 minutes*/)
        coroutineScope {
            val account = wearableDataStoreRepository.getAccount().first()
            account?.let {
                measureHeatRateUseCase(
                    id = it.id,
                    name = it.name,
                    tel = it.tel
                ).timeout(40.seconds)
                    .catch {
                        Log.e("[HEART_RATE_ERROR]", "${it.message}")
                        releaseWakeLock()
                        awaitCancellation()
                    }
                    .collectLatest { measure ->
                        Log.i("[MEASURE_HEART_RATE]", "${measure.heartRate}")
                        if (measure.availability == MeasuredAvailability.UNAVAILABLE_DEVICE_OFF_BODY ||
                            measure.availability == MeasuredAvailability.UNAVAILABLE
                        ) {
                            releaseWakeLock()
                            awaitCancellation()
                        }
                        measure.heartRate?.let { heartRate ->
                            releaseWakeLock()
                        }
                    }
            } ?: releaseWakeLock()
        }
    }

    private fun releaseWakeLock() {
        wakeLock.release()
    }

    companion object {
        private const val WORK_NAME = "heart_rate_measure_worker"
        fun enqueueWorker(context: Context) {
            val workRequest = OneTimeWorkRequestBuilder<HeartRateMeasureWorker>().build()
            WorkManager.getInstance(context).enqueueUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
        }
    }
}
