package com.sandy.logisync.data.network

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

private const val HEART_RATE = "heart_rate"
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
}
