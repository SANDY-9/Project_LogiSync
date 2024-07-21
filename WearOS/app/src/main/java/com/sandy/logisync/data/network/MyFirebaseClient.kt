package com.sandy.logisync.data.network

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.sandy.logisync.data.network.model.ArrestDTO
import com.sandy.logisync.data.network.model.CriticalPointDTO
import com.sandy.logisync.data.network.utils.ChildName
import com.sandy.logisync.data.network.utils.ChildName.TOKEN
import com.sandy.logisync.data.network.utils.IndexChildCreateUtil
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

class MyFirebaseClient @Inject constructor(
    private val ref: DatabaseReference,
) {

    fun getHeartRateCriticalPoint(
        id: String,
        onSuccess: (CriticalPointDTO) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val criticalPoint = ref.child(ChildName.USER).child(id).child(ChildName.CRITICAL_POINT).get()
        criticalPoint.addOnSuccessListener { criticalPoint ->
            if(criticalPoint.exists()) {
                val min = criticalPoint.child(ChildName.CRITICAL_POINT_MIN_HEART_RATE).getValue<Int>() ?: 0
                val max = criticalPoint.child(ChildName.CRITICAL_POINT_MAX_HEART_RATE).getValue<Int>() ?: 0
                val criticalPointDTO = CriticalPointDTO(min, max)
                onSuccess(criticalPointDTO)
            } else {
                onError(NullPointerException())
            }
        }.addOnFailureListener { error ->
            onError(error)
        }
    }

    fun updateHeartRate(
        id: String,
        bpm: Int,
        time: LocalDateTime,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val bpmChild = ref.child(ChildName.HEART_RATE).child(id)
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
        bpm: Int? = null,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val arrestDTO = ArrestDTO(arrestType, lat, lng, bpm)
        val arrestChild = ref.child(ChildName.ARREST).child(IndexChildCreateUtil.getDateIndexChild()).child(id)
        arrestChild.child(IndexChildCreateUtil.getTimeSecondsIndexChild(time)).setValue(arrestDTO)
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.i("[UPDATE_ARREST]", "${snapshot.value}", )
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

    fun getToken(
        id: String,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(TOKEN).child(id).get().addOnSuccessListener {
            val token = it.getValue<String>()
            token?.let { token ->
                onSuccess(token)
            } ?: onError(Exception())
        }.addOnFailureListener {
            onError(it)
        }
    }
}
