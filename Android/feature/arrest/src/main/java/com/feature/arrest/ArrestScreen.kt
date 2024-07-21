package com.feature.arrest

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ArrestScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "ArrestScreen")
    }
}
@Preview(name = "ArrestScreen")
@Composable
private fun PreviewArrestScreen() {
    ArrestScreen(rememberNavController())
}
