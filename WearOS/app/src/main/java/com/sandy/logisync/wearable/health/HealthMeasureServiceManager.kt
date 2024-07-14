package com.sandy.logisync.wearable.health

import android.util.Log
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.MeasureClient
import androidx.health.services.client.awaitWithException
import androidx.health.services.client.data.Availability
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.DataTypeAvailability
import androidx.health.services.client.data.DeltaDataType
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDateTime
import javax.inject.Inject

class HeartRateServiceManager @Inject constructor(
    private val measureClient: MeasureClient,
) {

    private suspend fun hasHeartRateCapability(): Boolean {
        val capabilities = measureClient.getCapabilitiesAsync().awaitWithException()
        return (DataType.HEART_RATE_BPM in capabilities.supportedDataTypesMeasure)
    }

    fun getHeartRateMeasureFlow() = callbackFlow {
        val callback = object : MeasureCallback {
            override fun onAvailabilityChanged(
                dataType: DeltaDataType<*, *>,
                availability: Availability
            ) {
                Log.e("확인", "onAvailabilityChanged: $availability")
                if (availability == DataTypeAvailability.UNAVAILABLE_DEVICE_OFF_BODY) {
                    trySendBlocking(MeasureMessage.MeasureAvailability(availability))
                }
            }

            override fun onDataReceived(data: DataPointContainer) {
                val heartRateBpm = data.getData(DataType.HEART_RATE_BPM)[0].value
                Log.e("확인", "onDataReceived: ${heartRateBpm}")
                val heartRateDTO = HeartRateDTO(
                    date = LocalDateTime.now(),
                    value = heartRateBpm,
                )
                trySendBlocking(MeasureMessage.MeasuredHeartRateData(heartRateDTO))
                if (heartRateBpm > 0) close()
            }
        }
        if (hasHeartRateCapability()) {
            measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, callback)
        }
        else {
            trySendBlocking(MeasureMessage.MeasureAvailability(DataTypeAvailability.UNAVAILABLE))
        }
        awaitClose {
            measureClient.unregisterMeasureCallbackAsync(DataType.HEART_RATE_BPM, callback)
        }
    }.distinctUntilChanged()
}
