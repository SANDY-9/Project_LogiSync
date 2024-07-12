package com.feature.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.sandy.service.MyWearableListenerService
import com.sandy.service.START_WEARABLE_LISTENER
import com.sandy.service.STOP_WEARABLE_LISTENER

@Composable
internal fun HomeWearableListener(
    context: Context?
) {
    DisposableEffect(key1 = Unit) {
        commandHeartRateService(context, START_WEARABLE_LISTENER)
        onDispose {
            commandHeartRateService(context, STOP_WEARABLE_LISTENER)
        }
    }
}

private fun commandHeartRateService(context: Context?, command: String) {
    val intent = Intent(context, MyWearableListenerService::class.java).apply {
        action = command
    }
    context?.startService(intent)
}
