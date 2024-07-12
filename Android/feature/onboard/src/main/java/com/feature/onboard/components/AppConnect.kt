package com.feature.onboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.feature.onboard.R

@Composable
fun AppConnect(
    isConnectedApp: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.onboard_connect_app),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )

        Icon(
            modifier = modifier
                .padding(vertical = 36.dp)
                .size(100.dp),
            painter = painterResource(id = com.core.desinsystem.R.drawable.temp_logo),
            contentDescription = null
        )

        Text(
            modifier = modifier.padding(bottom = 32.dp),
            text = stringResource(
                id = if (isConnectedApp) R.string.onboard_connect_app_success
                else R.string.onboard_connect_app_fail
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = modifier.weight(1f))

    }
}
