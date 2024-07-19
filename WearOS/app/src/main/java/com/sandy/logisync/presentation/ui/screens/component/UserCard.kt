package com.sandy.logisync.presentation.ui.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserCard(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "UserCard")
    }
}
@Preview(name = "UserCard")
@Composable
private fun PreviewUserCard() {
    UserCard()
}
