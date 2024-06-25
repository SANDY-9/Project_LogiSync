package com.feature.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignupScreen(
    navController: NavController,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Signup")
private fun SignupScreenPreview() {
    SignupScreen(rememberNavController())
}
