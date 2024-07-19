package com.sandy.logisync.workmanager

import android.content.Context
import android.location.Location
import android.os.PowerManager
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.domain.GetArrestCriticalPointUseCase
import com.sandy.logisync.domain.GetLocationUseCase
import com.sandy.logisync.domain.MeasureHeatRateUseCase
import com.sandy.logisync.domain.NotifyHeartBeatArrestUseCase
import com.sandy.logisync.domain.UpdateArrestUseCase
import com.sandy.logisync.model.Arrest
import com.sandy.logisync.model.MeasuredAvailability
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltWorker
class HeartRateMeasureWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val wearableDataStoreRepository: WearableDataStoreRepository,
    private val measureHeatRateUseCase: MeasureHeatRateUseCase,
    private val getArrestCriticalPointUseCase: GetArrestCriticalPointUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val updateArrestUseCase: UpdateArrestUseCase,
    private val notifyHeartBeatArrestUseCase: NotifyHeartBeatArrestUseCase,
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
            val account = wearableDataStoreRepository.getAccount()
            account?.let {
                val id = it.id
                val token =
                    "fPJzCSEXQNWzZYXGGx_dk1:APA91bFI6IwtGwE19SLN5SBbJrPOXyqoZkUlWdF3jhiaNWbi1GXrIoC7H-4H3qh4uGYOtZftLZj3yJbPY0uEii7itVsWnn7T7oBDr237_VxnYl6xfxbr-dzPGi5cTOd-C--naeBpY_kp"
                measureHeatRateUseCase(id, token)
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
                            val arrest = getArrestCriticalPointUseCase(id, heartRate.bpm)
                            if (arrest != Arrest.ArrestType.NONE) {
                                handleArrest(id, token, arrest, heartRate.bpm)
                            } else {
                                awaitCancellation()
                            }
                        }
                    }
            } ?: releaseWakeLock()
        }
    }

    private suspend fun handleArrest(
        id: String,
        token: String,
        arrest: Arrest.ArrestType,
        bpm: Int
    ) {
        getLocationUseCase().catch {
            Log.e("[LOCATION_ERROR]", "${it.message}")
        }.collectLatest { location ->
            notifyHeartBeatArrest(id, token, arrest, location)
            updateArrest(id, arrest, location, bpm)
        }
    }

    private suspend fun notifyHeartBeatArrest(
        id: String,
        token: String,
        arrest: Arrest.ArrestType,
        location: Location,
    ) {
        coroutineScope {
            notifyHeartBeatArrestUseCase(id, token, arrest, location)
                .catch {
                    Log.e("[ARREST_FCM_RESULT]", "${it.message}")
                    awaitCancellation()
                }
                .collectLatest {
                    Log.i("[ARREST_FCM_RESULT]", "$it")
                    awaitCancellation()
                }
        }
    }
    private suspend fun updateArrest(
        id: String,
        arrest: Arrest.ArrestType,
        location: Location,
        bpm: Int
    ) {
        coroutineScope {
            updateArrestUseCase(id, arrest, location, bpm)
                .catch {
                    Log.e("[ARREST_SAVE_RESULT]", "${it.message}")
                    awaitCancellation()
                }
                .collectLatest {
                    Log.i("[ARREST_SAVE_RESULT]", "$it")
                    awaitCancellation()
                }
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
