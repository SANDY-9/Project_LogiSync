package com.sandy.logisync.presentation.ui.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Text

@Composable
fun HeartRateLoading(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "로딩중이오")
    }
}

@Preview(name = "HeartRateLoading")
@Composable
private fun PreviewHeartRateLoading() {
    HeartRateLoading()
}
