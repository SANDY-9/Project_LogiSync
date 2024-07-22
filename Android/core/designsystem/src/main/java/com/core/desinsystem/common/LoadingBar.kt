package com.core.desinsystem.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLoadingBar(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier.size(20.dp),
        color = Color.White,
        trackColor = Color.Transparent,
        strokeWidth = 3.dp,
        strokeCap = StrokeCap.Round
    )
}
