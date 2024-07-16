package com.feature.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AdminScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "AdminScreen")
    }
}

@Preview(name = "AdminScreen")
@Composable
private fun PreviewAdminScreen() {
    AdminScreen(rememberNavController())
}
