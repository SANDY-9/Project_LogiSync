package com.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.BasicOutlinedButton
import com.core.desinsystem.common.LogiCard
import com.core.desinsystem.icons.Bluetooth
import com.core.desinsystem.icons.BluetoothOff
import com.feature.home.R

@Composable
fun PairingInfo(
    deviceName: String,
    isPairedWatch: Boolean,
    onConnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_device_pairing_title),
            style = MaterialTheme.typography.headlineSmall,
           // color = Color.White,
        )
        Spacer(modifier = modifier.size(12.dp))
        LogiCard{
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                text = deviceName,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = modifier.size(8.dp))
            val connectModifier = modifier.align(Alignment.CenterHorizontally)
            if (isPairedWatch) {
                ConnectedWatch(modifier = connectModifier)
            }
            else {
                DisConnectedDevice(
                    modifier = connectModifier,
                    onConnect = onConnect,
                )
            }
        }
    }
}

@Composable
private fun ConnectedWatch(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(18.dp),
            imageVector = Icons.Bluetooth,
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.home_device_pairing_connect),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun DisConnectedDevice(
    onConnect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Icon(
                modifier = modifier.size(18.dp),
                imageVector = Icons.BluetoothOff,
                contentDescription = null,
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.home_device_pairing_deconnect),
                style = MaterialTheme.typography.titleSmall
            )
        }
        BasicOutlinedButton(
            title = stringResource(id = R.string.home_device_pairing_connect_title),
            onClick = onConnect,
        )
    }
}
