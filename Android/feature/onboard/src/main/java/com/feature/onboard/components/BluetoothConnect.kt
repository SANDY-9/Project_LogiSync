package com.feature.onboard.components

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.common.BasicOutlinedButton
import com.core.desinsystem.lottie.LottieBluetooth
import com.core.desinsystem.lottie.LottieBluetoothOk
import com.core.domain.enums.BluetoothState
import com.feature.onboard.R

@Composable
fun BluetoothConnect(
    bluetoothState: BluetoothState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.height(150.dp))
        Text(
            text = stringResource(id = R.string.onboard_connect_bluetooth_desc),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = (-0.1).sp,
                fontSize = 18.sp
            ),
        )
        if(bluetoothState != BluetoothState.ON) {
            LottieBluetooth(modifier = modifier.size(150.dp))
        }
        else {
            LottieBluetoothOk(modifier = modifier.size(150.dp))
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            modifier = modifier.padding(bottom = 32.dp),
            text = stringResource(
                id = when (bluetoothState) {
                    BluetoothState.DISABLED -> R.string.onboard_connect_bluetooth_disabled
                    BluetoothState.PERMISSION_DENIED -> R.string.onboard_connect_bluetooth_deny
                    BluetoothState.OFF -> R.string.onboard_connect_bluetooth_fail
                    BluetoothState.ERROR -> R.string.onboard_connect_bluetooth_error
                    else -> R.string.onboard_connect_bluetooth_success
                }
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = (-0.1).sp,
                fontSize = 15.sp
            ),
            color = Color.Gray
        )
        if (bluetoothState == BluetoothState.PERMISSION_DENIED) {
            BluetoothPermissionButton()
        }
        if (bluetoothState == BluetoothState.OFF) {
            BluetoothConnectButton()
        }
        Spacer(modifier = modifier.weight(1f))
    }
}

private val bluetoothPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
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

@Composable
private fun BluetoothPermissionButton(
    modifier: Modifier = Modifier,
) {
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {
        Log.e("확인", "BluetoothConnectButton: $it")
        val isGranted = !it.containsValue(false)
    }

    BasicOutlinedButton(
        title = stringResource(id = R.string.onboard_connect_bluetooth_permission),
        onClick = { permissionLauncher.launch(bluetoothPermissions) }
    )
}

@Composable
private fun BluetoothConnectButton(
    modifier: Modifier = Modifier,
) {
    val bluetoothLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        Log.e("확인", "BluetoothConnectButton: $it")
    }

    BasicOutlinedButton(
        title = stringResource(id = R.string.onboard_connect_bluetooth),
        onClick = {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            bluetoothLauncher.launch(intent)
        }
    )
}
