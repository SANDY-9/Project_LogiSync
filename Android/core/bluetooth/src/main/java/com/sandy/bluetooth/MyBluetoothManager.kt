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
import com.sandy.bluetooth.utils.BluetoothDisabledException
import com.sandy.bluetooth.utils.BluetoothPermissionDeniedException
import javax.inject.Inject

class MyBluetoothManager @Inject constructor(
    private val context: Context,
    private val bluetoothManager: BluetoothManager,
) {

    fun isBluetoothEnabled(): Boolean {
        val adapter = bluetoothManager.adapter ?: throw BluetoothDisabledException()
        if (!isGrantedBluetoothPermission()) throw BluetoothPermissionDeniedException()
        return adapter.isEnabled
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
