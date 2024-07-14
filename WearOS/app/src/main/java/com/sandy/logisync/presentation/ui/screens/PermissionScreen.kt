package com.sandy.logisync.presentation.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun PermissionScreen(
    onPermission: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "앱 사용을 위한\n권한 허용이 필요합니다.",
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = modifier.height(8.dp))
        Button(
            modifier = modifier
                .padding(4.dp)
                .size(100.dp, 40.dp),
            onClick = onPermission,
        ) {
            Text(text = "권한허용")
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewPermissionScreen() {
    PermissionScreen({})
}
