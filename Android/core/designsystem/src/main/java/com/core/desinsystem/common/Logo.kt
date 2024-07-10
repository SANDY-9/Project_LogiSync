package com.core.desinsystem.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    Text(
        text = "LogiSync",
        style = MaterialTheme.typography.headlineLarge
    )
}
