package com.sandy.service

import android.content.Intent
import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MyWearableListenerService : WearableListenerService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            if (it.action == STOP_WEARABLE_LISTENER) stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onMessageReceived(p0: MessageEvent) {
        val data = p0.data.toString(Charsets.UTF_8)
        when (p0.path) {
            MessagePath.GET_LOGIN_RESPONSE.path -> {
                Log.e("확인", "onMessageReceived: $data")
            }

            MessagePath.GET_HEART_RATE.path -> {
                _heartRate.value = data.toInt()
            }

            MessagePath.GET_ARREST.path -> {
            }
        }
    }

    companion object {
        private val _heartRate = MutableStateFlow(0)
        val heartRate: StateFlow<Int> get() = _heartRate
    }
}
