package com.core.firebase

import com.core.firebase.common.Constants.CRITICAL_POINT
import com.core.firebase.common.Constants.USERS
import com.core.firebase.model.CriticalPointDTO
import com.core.firebase.model.UserDTO
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject

class UserClient @Inject constructor(
    private val ref: DatabaseReference,
) {

    fun getUser(
        id: String,
        onSuccess: (UserDTO?) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).child(id).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val user = snapshot.getValue<UserDTO>() ?: return@addOnSuccessListener
                val criticalPoint = snapshot.child(CRITICAL_POINT).getValue<CriticalPointDTO>() ?: return@addOnSuccessListener
                onSuccess(user.copy(criticalPoint = criticalPoint))
            }
            else {
                onError(Exception())
            }
        }.addOnFailureListener {
            onError(it)
        }
    }

    fun getUserList(
        onSuccess: (List<UserDTO?>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val userList = snapshot.children.map {
                    val user = it.getValue<UserDTO>()
                    val criticalPoint = it.child(CRITICAL_POINT).getValue<CriticalPointDTO>()
                    user?.copy(
                        id = it.key,
                        criticalPoint = criticalPoint,
                    )
                }
                onSuccess(userList)
            }
            else {
                onSuccess(emptyList())
            }
        }.addOnFailureListener {
            onError(it)
        }
    }
}
