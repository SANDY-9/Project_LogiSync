package com.sandy.logisync.wearable.health

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sandy.logisync.workmanager.HeartRateMonitoringWorker

class StartupReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != Intent.ACTION_BOOT_COMPLETED) return
        context?.let {
            HeartRateMonitoringWorker.registerWorker(it)
        }
    }
}
