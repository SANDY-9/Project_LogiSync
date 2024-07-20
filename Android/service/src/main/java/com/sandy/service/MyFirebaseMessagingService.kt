package com.sandy.service


import android.util.Log
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
                account?.let {
                    val id = it.id
                    client.updateToken(
                        id = id,
                        token = token,
                        onSuccess = {
                            Log.i("[TOKEN]", "sendRegistrationToServer: $it", )
                        },
                        onError = {
                            Log.e("[TOKEN]", "sendRegistrationToServer: $it", )
                        }
                    )
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

        createNotification(message)

    }

    @SuppressLint("MissingPermission")
    private fun createNotification(message: RemoteMessage) {
        val notificationManager = NotificationManagerCompat.from(applicationContext)

        var builder : NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(notificationManager.getNotificationChannel(CHANNEL_ID) == null){
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH,
                )
                notificationManager.createNotificationChannel(channel)
            }
            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        }else{
            builder = NotificationCompat.Builder(applicationContext)
        }

        val title = message.notification?.title
        val body = message.notification?.body

        builder.setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(com.core.desinsystem.R.drawable.temp_logo)

        val notification = builder.build()
        notificationManager.notify(1, notification)
    }

    companion object {
        private const val CHANNEL_ID = "channel_logisync"
        private const val CHANNEL_NAME = "channel_logisync_arrest"
    }
}
