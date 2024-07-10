package com.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Bluetooth
import com.feature.home.R

@Composable
fun PairingInfo(
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_gear_pairing_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.size(12.dp))
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                text = "선미의 Watch4",
                style = MaterialTheme.typography.titleLarge
            )
            ConnectedWatch(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp)
            )
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
        Icon(imageVector = Icons.Bluetooth, contentDescription = null)
        Text(
            text = stringResource(id = R.string.home_gear_pairing_connect),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(name = "PairingInfo")
@Composable
private fun PreviewPairingInfo() {
    PairingInfo()
}
