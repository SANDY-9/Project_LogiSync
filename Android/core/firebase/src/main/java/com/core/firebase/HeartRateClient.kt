package com.core.firebase

import com.core.firebase.common.Constants.CRITICAL_POINT
import com.core.firebase.common.Constants.HEART_RATE
import com.core.firebase.common.Constants.MAX_CRITICAL_POINT
import com.core.firebase.common.Constants.MAX_HEART_RATE
import com.core.firebase.common.Constants.MIN_CRITICAL_POINT
import com.core.firebase.common.Constants.MIN_HEART_RATE
import com.core.firebase.common.Constants.USERS
import com.core.firebase.model.HeartRateDTO
import com.core.firebase.utils.child
import com.core.firebase.utils.dateStr
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class HeartRateClient @Inject constructor(
    private val ref: DatabaseReference,
) {

    fun getLastHeartRate(
        id: String,
        onSuccess: (HeartRateDTO?) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val heartRate = snapshot.children.last().children.last().children.last()
                    val heartRateDTO = HeartRateDTO(
                        bpm = heartRate.value.toString().toInt(),
                        date = heartRate.key.toString(),
                    )
                    onSuccess(heartRateDTO)
                }
                else {
                    onSuccess(null)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                onError(error.toException())
            }
        }
        ref.child(HEART_RATE).child(id).orderByValue().limitToLast(1).addValueEventListener(listener)
    }

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
                close(it)
            }
        awaitClose()
    }

    fun getHeartRateByPeriod(
        id: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ) = callbackFlow {
        val startChild = startDate.child()
        val endChild = endDate.child()
        val start = startDate.dateStr().toInt()
        val end = endDate.dateStr().toInt()
        ref.child(HEART_RATE).child(id).orderByKey().startAt(startChild).endAt(endChild).get()
            .addOnSuccessListener { snapshot ->
                val measuredHeartRate = mutableListOf<HeartRateDTO>()
                snapshot.children.forEach { yearChild ->
                    val month = yearChild.key ?: return@forEach
                    yearChild.children.forEach { dayChild ->
                        val day = dayChild.key ?: return@forEach
                        val date = (month + day).toInt()
                        if(date in start..end) {
                            dayChild.children.forEach {
                                val heartRateDTO = HeartRateDTO(
                                    date = it.key ?: return@forEach,
                                    bpm = it.value.toString().toInt(),
                                )
                                measuredHeartRate.add(heartRateDTO)
                            }
                        }
                    }
                }
                trySend(measuredHeartRate.toList())
            }
            .addOnFailureListener {
                close(it)
            }
        awaitClose()
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
