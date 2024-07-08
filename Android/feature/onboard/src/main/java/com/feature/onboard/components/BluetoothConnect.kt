package com.feature.onboard.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.feature.onboard.R
import com.sandy.bluetooth.BluetoothState
import com.sandy.bluetooth.MyBluetoothManager
import com.sandy.designsystem.icons.Bluetooth

@Composable
fun BluetoothConnect(
    bluetoothState: BluetoothState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.weight(1f))
        Icon(
            modifier = modifier
                .padding(bottom = 36.dp)
                .size(100.dp),
            imageVector = Icons.Bluetooth,
            contentDescription = null
        )

        Text(text = stringResource(id = R.string.onboard_connect_bluetooth_desc))
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
            style = MaterialTheme.typography.bodyLarge,
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

    Button(
        modifier = modifier.defaultMinSize(
            minHeight = 1.dp,
        ),
        contentPadding = PaddingValues(
            vertical = 6.dp,
            horizontal = 16.dp
        ),
        onClick = {
            permissionLauncher.launch(MyBluetoothManager.bluetoothPermissions)
        }
    ) {
        Text(
            text = stringResource(id = R.string.onboard_connect_bluetooth_permission),
            style = MaterialTheme.typography.bodySmall
        )
    }
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

    Button(
        modifier = modifier.defaultMinSize(
            minHeight = 1.dp,
        ),
        contentPadding = PaddingValues(
            vertical = 6.dp,
            horizontal = 16.dp
        ),
        onClick = {
            val intent = MyBluetoothManager.getBluetoothIntent()
            bluetoothLauncher.launch(intent)
        }
    ) {
        Text(
            text = stringResource(id = R.string.onboard_connect_bluetooth),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
