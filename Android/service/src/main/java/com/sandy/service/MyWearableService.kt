package com.sandy.service

import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyWearableService : WearableListenerService() {

    override fun onMessageReceived(p0: MessageEvent) {
        Log.e("확인", "onMessageReceived: $p0")
    }
}
