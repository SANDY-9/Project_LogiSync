package com.feature.onboard.components

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WatchConnection(
    modifier: Modifier = Modifier
) {
    BluetoothConnectListener()
    Box(modifier) {
        Text(text = "WatchConnection")
    }
}

@Composable
private fun BluetoothConnectListener(
    context: Context = LocalContext.current,
) {
    DisposableEffect(Unit) {
        val broadcastReceiver = object : BroadcastReceiver() {
            @SuppressLint("MissingPermission")
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        val device = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            intent.getParcelableExtra(
                                BluetoothDevice.EXTRA_DEVICE,
                                BluetoothDevice::class.java
                            )
                        }
                        else {
                            intent.getParcelableExtra<BluetoothDevice>(
                                BluetoothDevice.EXTRA_DEVICE
                            )
                        }
                        val deviceName = device?.name
                        val deviceMacAddress = device?.address // MAC address
                        Log.e("확인", "onReceive: $deviceName", )
                    }
                }
            }
        }
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(broadcastReceiver, intentFilter)

        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }
}

@Preview(name = "WatchConnection")
@Composable
private fun PreviewWatchConnection() {
    WatchConnection()
}
