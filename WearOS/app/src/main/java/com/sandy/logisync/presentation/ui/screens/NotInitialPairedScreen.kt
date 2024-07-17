package com.sandy.logisync.presentation.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.sandy.logisync.R

@Composable
fun NotInitialPairedScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Rounded.Info,
            tint = Color.LightGray,
            contentDescription = null
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.auth_error1),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = R.string.auth_error2),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption2
        )
    }
}

@Preview(device = WearDevices.SMALL_ROUND, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewNotInitialPairedScreen() {
    NotInitialPairedScreen()
}
