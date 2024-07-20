package com.core.firebase

import com.core.firebase.common.Constants.CRITICAL_POINT
import com.core.firebase.common.Constants.HEART_RATE
import com.core.firebase.common.Constants.MAX_CRITICAL_POINT
import com.core.firebase.common.Constants.MAX_HEART_RATE
import com.core.firebase.common.Constants.MIN_CRITICAL_POINT
import com.core.firebase.common.Constants.MIN_HEART_RATE
import com.core.firebase.common.Constants.USERS
import com.core.firebase.model.HeartRateDTO
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeartRateClient @Inject constructor(
    private val ref: DatabaseReference,
) {
    fun getHeartRateByDate(
        id: String,
        yearMonth: String,
        day: Int,
    ) = callbackFlow<Map<String, Int>> {
        ref.child(HEART_RATE).child(id).child(yearMonth).child(day.toString()).get()
            .addOnSuccessListener { snapshot ->
                val measuredHeartRate = snapshot.getValue<Map<String, Int>>()
                trySend(measuredHeartRate ?: emptyMap())
            }
            .addOnFailureListener {
                error(it)
            }
        awaitClose()
    }
    fun getLastHeartRate(
        id: String,
        onSuccess: (HeartRateDTO?) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(HEART_RATE).child(id).orderByKey().limitToLast(1).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val heartRate = snapshot.children.last().children.last().children.first()
                    val heartRateDTO = HeartRateDTO(
                        bpm = heartRate.value.toString().toInt(),
                        date = heartRate.key.toString(),
                    )
                    onSuccess(heartRateDTO)
                }
                else {
                    onSuccess(null)
                }
            }.addOnFailureListener { onError(it) }
    }

    suspend fun updateHeartRateCriticalPoint(
        id: String,
        min: Int = MIN_HEART_RATE,
        max: Int = MAX_HEART_RATE,
    ) = withContext(Dispatchers.IO) {
        val map = mapOf(
            MAX_CRITICAL_POINT to max,
            MIN_CRITICAL_POINT to min,
        )
        ref.child(USERS).child(id).child(CRITICAL_POINT).setValue(map)
    }
}
