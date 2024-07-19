package com.sandy.logisync.data.network

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.sandy.logisync.data.network.model.ArrestDTO
import com.sandy.logisync.data.network.utils.IndexChildCreateUtil
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

private const val HEART_RATE = "heart_rate"
private const val ARREST = "arrest"
private const val BPM = "bpm"

class MyFirebaseClient @Inject constructor(
    private val ref: DatabaseReference,
) {

    fun updateHeartRate(
        id: String,
        bpm: Int,
        time: LocalDateTime,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        Log.e("확인", "updateHeartRate: $id, $bpm, $time")
        val bpmChild = ref.child(HEART_RATE).child(id).child(BPM)
        bpmChild.child(IndexChildCreateUtil.getYearMonthIndexChild())
            .child(IndexChildCreateUtil.getDayIndexChild())
            .child(IndexChildCreateUtil.getTimeIndexChild(time))
            .setValue(bpm)
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    onSuccess(true)
                }
                else {
                    onError(IOException())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.toException())
            }
        }
        bpmChild.addListenerForSingleValueEvent(dataListener)
    }

    fun updateArrest(
        id: String,
        arrestType: String,
        lat: Double,
        lng: Double,
        time: LocalDateTime,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val arrestDTO = ArrestDTO(arrestType, lat, lng)
        val arrestChild = ref.child(ARREST).child(IndexChildCreateUtil.getDateIndexChild()).child(id)
        arrestChild.child(IndexChildCreateUtil.getTimeSecondsIndexChild(time))
            .setValue(arrestDTO)
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.e("확인", "onDataChange: ${snapshot.value}", )
                    onSuccess(true)
                }
                else {
                    onError(IOException())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.toException())
            }
        }
       arrestChild.addListenerForSingleValueEvent(dataListener)
    }
}
