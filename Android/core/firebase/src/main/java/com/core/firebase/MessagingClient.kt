package com.core.firebase

import android.util.Log
import com.core.firebase.common.Constants.ARREST
import com.core.firebase.common.Constants.TOKEN
import com.google.firebase.database.DatabaseReference
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class MessagingClient @Inject constructor(
    private val ref: DatabaseReference,
    private val messaging: FirebaseMessaging,
) {

    private fun createToken(
        onToken: (String) -> Unit,
        onError: (Exception) -> Unit,
    ) {
        messaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                onError(task.exception ?: Exception())
            }
            else {
                val token = task.result
                Log.e("확인", "getToken: $token", )
                onToken(token)
            }
        }
    }

    fun registerToken(
        id: String,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        createToken(
            onToken = { token ->
                ref.child(TOKEN).child(id).setValue(token)
                    .addOnSuccessListener {
                        onSuccess(true)
                    }
                    .addOnFailureListener {
                        onError(it)
                    }
            },
            onError = {
                onError(it)
            },
        )
    }

    fun updateToken(
        id: String,
        token: String,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        ref.child(TOKEN).child(id).setValue(token)
            .addOnSuccessListener {
                onSuccess(true)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    fun subscribeToArrestTopic() {
        messaging.subscribeToTopic(ARREST)
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d("[FCM_SUBSCRIBE]", msg)
            }
    }

    fun unsubscribeToArrestTopic() {
        messaging.unsubscribeFromTopic(ARREST)
            .addOnCompleteListener { task ->
                var msg = "UnSubscribed"
                if (!task.isSuccessful) {
                    msg = "UnSubscribe failed"
                }
                Log.d("[FCM_UNSUBSCRIBE]", msg)
            }
    }

}
