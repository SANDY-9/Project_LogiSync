package com.feature.onboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feature.onboard.R
import com.sandy.designsystem.icons.Bluetooth

@Composable
fun BluetoothPhase(
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
        Text(text = stringResource(id = R.string.onboard_connect_bluetooth_success))
        Text(
            text = stringResource(id = R.string.onboard_connect_bluetooth_fail),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.weight(1f))
    }
}

@Preview(name = "BluetoothPhase")
@Composable
private fun PreviewBluetoothPhase() {
    BluetoothPhase()
}
