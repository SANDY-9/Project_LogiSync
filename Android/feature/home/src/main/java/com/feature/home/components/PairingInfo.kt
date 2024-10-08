package com.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.LogiCard
import com.core.desinsystem.icons.Bluetooth
import com.core.desinsystem.icons.BluetoothOff
import com.core.desinsystem.theme.LogiBlue
import com.feature.home.R

@Composable
internal fun PairingInfo(
    checkWearableLogin: Boolean,
    deviceName: String,
    isPairedWatch: Boolean,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_device_pairing_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.size(12.dp))
        LogiCard{
            if (!checkWearableLogin) {
                NotValidWatch()
            }
            else {
                Text(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    text = deviceName,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = modifier.size(8.dp))
                val connectModifier = modifier.align(Alignment.CenterHorizontally)
                if (isPairedWatch) {
                    ConnectedWatch(modifier = connectModifier)
                }
                else {
                    DisConnectedDevice(modifier = connectModifier)
                }
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
            tint = LogiBlue,
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.home_device_pairing_connect),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun DisConnectedDevice(
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
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.home_device_pairing_connect_title),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
        )
    }
}

@Composable
private fun NotValidWatch(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = modifier.size(20.dp),
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            tint = Color.Gray,
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.home_device_not_same),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
    }
}
