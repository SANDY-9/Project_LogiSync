package com.core.firebase

import com.core.firebase.common.Constants.STAFF
import com.core.firebase.model.StaffDTO
import com.core.model.Staff
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class StaffClient @Inject constructor(
    private val ref: DatabaseReference,
) {
    fun getStaffList(
        onSuccess: (List<Staff>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(STAFF).get().addOnSuccessListener { snapshot ->
            val staffList = mutableListOf<Staff>()
            snapshot.children.forEach { name ->
                name.children.forEach { date ->
                    val staffDTO = date.getValue(StaffDTO::class.java)
                    staffDTO?.let {
                        staffList.add(
                            Staff(
                                name = name.key!!,
                                bpm = it.bpm,
                                walk = it.walk,
                                km = it.km,
                            )
                        )
                    }
                }
            }
            onSuccess(staffList)
        }.addOnFailureListener {
            onError(it)
        }
    }
}
