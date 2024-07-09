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
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyBluetoothManager @Inject constructor(
    private val context: Context,
    private val bluetoothManager: BluetoothManager,
) {

    fun getBluetoothStateFlow(interval: Long = 1200L): Flow<BluetoothState> = flow {
        while (true) {
            emit(getBluetoothState())
            delay(interval)
        }
    }.distinctUntilChanged()

    @SuppressLint("MissingPermission")
    fun isBondedWatch(): Boolean {
        val bluetoothAdapter = bluetoothManager.adapter
        val devices = bluetoothAdapter.bondedDevices.filter {
            it.type == BluetoothDevice.DEVICE_TYPE_DUAL && it.name.contains("Watch")
        }
        return devices.isNotEmpty()
    }

    private fun getBluetoothState(): BluetoothState {
        try {
            val bluetoothAdapter = bluetoothManager.adapter ?: return BluetoothState.DISABLED
            if (!isGrantedBluetoothPermission()) return BluetoothState.PERMISSION_DENIED
            return if (bluetoothAdapter.isEnabled) {
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
