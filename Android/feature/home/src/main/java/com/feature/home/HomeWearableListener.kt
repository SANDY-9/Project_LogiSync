package com.feature.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable

@Composable
internal fun HomeWearableListener(
    context: Context
) {
    val messageListener = MessageClient.OnMessageReceivedListener {
        val data = it.data.toString(Charsets.UTF_8)
        Log.e("확인", "onMessageReceived: $data", )
        when (it.path) {
            MessagePath.GET_LOGIN_RESPONSE.path -> {
                Log.e("확인", "onMessageReceived: $data")
            }

            MessagePath.GET_HEART_RATE.path -> {
                Log.e("확인", "onMessageReceived: 무사히 도착 $data", )
            }

            MessagePath.GET_ARREST.path -> {
            }
        }
    }
    DisposableEffect(key1 = Unit) {
        Wearable.getMessageClient(context).addListener(messageListener)

        onDispose {
            Wearable.getMessageClient(context).removeListener(messageListener)
        }
    }
}


private enum class MessagePath(val path: String) {
    GET_LOGIN_RESPONSE("/login_transcription"),
    GET_HEART_RATE("/heart_rate_transcription"),
    GET_ARREST("/arrest_transcription"),
}
