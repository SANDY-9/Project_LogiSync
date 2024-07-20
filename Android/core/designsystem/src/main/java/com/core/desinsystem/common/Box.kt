package com.core.desinsystem.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxLayout(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(
        horizontal = 20.dp,
        vertical = 8.dp
    ),
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(padding)
    ) {
        content()
    }
}
