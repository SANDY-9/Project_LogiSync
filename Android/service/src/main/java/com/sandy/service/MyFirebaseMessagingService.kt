package com.sandy.service

import android.util.Log
import com.core.domain.repository.AuthPrefsRepository
import com.core.firebase.MessagingClient
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var client: MessagingClient
    @Inject
    lateinit var authPrefsRepository: AuthPrefsRepository

    override fun onNewToken(token: String) {
        Log.i("[TOKEN]", "onNewToken: $token", )
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            authPrefsRepository.getAccount().collect { account ->
                val id = account.id
                client.updateToken(id, token) {
                    Log.e("[TOKEN]", "sendRegistrationToServer: $it", )
                }
            }
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("확인", "onMessageReceived: ${message.rawData}", )
        Log.e("확인", "onMessageReceived: ${message.messageId}", )
        Log.e("확인", "onMessageReceived: ${message.data}", )
        Log.e("확인", "onMessageReceived: ${message.to}", )
        Log.e("확인", "onMessageReceived: ${message.from}", )
        Log.e("확인", "onMessageReceived: ${message.notification?.body}", )
        /*if (message.data.isNotEmpty()) {
            Log.d("확인", "Message data payload: ${message.data}")

            // Check if data needs to be processed by long running job
            if (needsToBeScheduled()) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow()
            }
        }

        // Check if message contains a notification payload.
        message.notification?.let {
            Log.d("확인", "Message Notification Body: ${it.body}")
        }*/

    }

}
