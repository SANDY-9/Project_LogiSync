package com.core.firebase

import android.util.Log
import com.core.firebase.common.Constants.TOKEN
import com.core.firebase.common.Constants.USERS
import com.google.firebase.database.DatabaseReference
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class MessagingClient @Inject constructor(
    private val ref: DatabaseReference,
    private val messaging: FirebaseMessaging,
) {

    fun getToken(
        onToken: (String) -> Unit,
        onError: (Exception?) -> Unit,
    ) {
        messaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                onError(task.exception)
            }
            else {
                val token = task.result
                Log.e("확인", "getToken: $token", )
                onToken(token)
            }
        }
    }

    fun updateToken(
        id: String,
        token: String,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(USERS).child(id).child(TOKEN).setValue(token).addOnFailureListener {
            onError(it)
        }
    }


}
