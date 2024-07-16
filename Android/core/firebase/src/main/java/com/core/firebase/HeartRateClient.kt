package com.core.firebase

import com.core.firebase.common.Constants.HEART_RATE
import com.core.firebase.common.Constants.HEART_RATE_BPM
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class HeartRateClient @Inject constructor(
    private val ref: DatabaseReference,
) {
    fun getHeartRateByDate(
        id: String,
        yearMonth: String,
        day: Int,
    ) = callbackFlow<Map<String, Int>> {
        ref.child(HEART_RATE).child(id).child(HEART_RATE_BPM)
            .child(yearMonth).child(day.toString())
            .get()
            .addOnSuccessListener { snapshot ->
                val measuredHeartRate = snapshot.getValue<Map<String, Int>>()
                trySend(measuredHeartRate ?: emptyMap())
            }
            .addOnFailureListener {
                error(it)
            }
        awaitClose()
    }
}
