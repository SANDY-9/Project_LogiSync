package com.sandy.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.core.enum.BluetoothState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyBluetoothManager @Inject constructor(
    private val context: Context,
    private val bluetoothManager: BluetoothManager,
) {

    fun getBluetoothState(): BluetoothState {
        try {
            val adapter = bluetoothManager.adapter ?: return BluetoothState.DISABLED
            if (!isGrantedBluetoothPermission()) return BluetoothState.PERMISSION_DENIED
            return if (adapter.isEnabled) {
                BluetoothState.ON
            }
            else {
                BluetoothState.OFF
            }
        } catch (e: Exception) {
            return BluetoothState.ERROR
        }
    }

    fun isGrantedBluetoothPermission(): Boolean {
        val checkBluetoothPermission =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT)
            }
            else {
                ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH)
            }
        return checkBluetoothPermission == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    fun getBluetoothPairedWatch(): List<BluetoothDevice> {
        val adapter = bluetoothManager.adapter
        val devices = adapter.bondedDevices.filter {
            it.type == BluetoothDevice.DEVICE_TYPE_DUAL// && it.name.contains("Watch")
        }
        Log.e("확인", "isBondedWatch: ${devices}")
        return devices
    }

    @SuppressLint("MissingPermission")
    fun getDeviceName(device: BluetoothDevice): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            device.alias ?: device.name
        }
        else {
            device.name
        }
    }

    companion object {
        val bluetoothPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN,
            )
        }
        else {
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
            )
        }

        fun getBluetoothIntent(): Intent {
            return Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        }
    }
}
