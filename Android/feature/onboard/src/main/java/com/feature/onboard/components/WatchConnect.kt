package com.feature.onboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.DevicesWearables
import com.feature.onboard.R
import com.sandy.bluetooth.BluetoothState

@Composable
fun WatchConnect(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.onboard_connect_watch),
            style = MaterialTheme.typography.titleLarge
        )

        Icon(
            modifier = modifier
                .padding(vertical = 50.dp)
                .size(100.dp),
            imageVector = Icons.DevicesWearables,
            contentDescription = null
        )

        Text(
            text = stringResource(
                id = R.string.onboard_connect_watch_success
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier = modifier.padding(bottom = 32.dp),
            text = stringResource(
                id = R.string.onboard_connect_watch_fail
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = modifier.height(16.dp))
        Spacer(modifier = modifier.weight(1f))
    }
}
