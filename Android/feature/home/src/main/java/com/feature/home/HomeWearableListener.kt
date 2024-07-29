package com.feature.home

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable

@Composable
internal fun HomeWearableListener(
    context: Context,
    onLoginResponse: (Boolean) -> Unit,
) {
    val messageListener = MessageClient.OnMessageReceivedListener {
        val data = it.data.toString(Charsets.UTF_8)
        if(it.path == MessagePath.GET_LOGIN_RESPONSE.path) {
            onLoginResponse(data == "OK")
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
