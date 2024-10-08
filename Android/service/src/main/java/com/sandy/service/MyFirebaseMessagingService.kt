package com.sandy.service


import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.core.domain.repository.AuthPrefsRepository
import com.core.firebase.MessagingClient
import com.core.model.Arrest
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
        createNotification(message)
    }

    @SuppressLint("MissingPermission")
    private fun createNotification(message: RemoteMessage) {
        val title = message.notification?.title
        val body = message.notification?.body
        val notification = NotificationCompat
            .Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(com.core.desinsystem.R.drawable.temp_logo)
            .build()
        notifyNotification(notification)
    }

    @SuppressLint("MissingPermission")
    private fun notifyNotification(notification: Notification) {
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createChannel()
        notificationManager.notify(1, notification)
    }

    private fun NotificationManagerCompat.createChannel() {
        if(getNotificationChannel(CHANNEL_ID) == null){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            )
            createNotificationChannel(channel)
        }
    }

    private val formatter = DateTimeFormatter.ISO_DATE_TIME
    private fun Map<String, String>.toArrest(): Arrest {

        val arrestType = this["arrestType"]?.let { Arrest.ArrestType.valueOf(it) } ?: Arrest.ArrestType.NORMAL
        val id = this["id"] ?: ""
        val lat = this["lat"]?.toDouble() ?: 0.0
        val lng = this["lng"]?.toDouble() ?: 0.0
        val tel = this["tel"] ?: ""
        val name = this["name"] ?: ""
        val time = this["time"]?.let { LocalDateTime.parse(it, formatter) } ?: LocalDateTime.now()
        val bpm  = this["bpm"]?.toInt()

        return Arrest(
            id = id,
            arrestType = arrestType,
            lat = lat,
            lng = lng,
            tel = tel,
            name = name,
            time = time,
            bpm = bpm
        )
    }

    companion object {
        private const val CHANNEL_ID = "channel_logisync"
        private const val CHANNEL_NAME = "channel_logisync_arrest"
    }
}
