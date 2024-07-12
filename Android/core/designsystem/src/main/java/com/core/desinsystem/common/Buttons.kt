package com.core.desinsystem.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun BasicTextButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.clickable(onClick = onClick),
        text = title,
    )
}

@Composable
fun NextButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onClick,
        enabled = enabled,
    ) {
        Text(
            text = title
        )

    }
}

@Composable
fun BasicOutlinedButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 16.dp),
        onClick = onClick,
    ) {
        Text(text = title)
    }
}
