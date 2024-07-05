package com.feature.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "연결되어 있는 스마트 워치를 확인하고 있습니다.")
    }
}

@Preview(name = "OnboardingScreen")
@Composable
private fun PreviewOnboardingScreen() {
    OnboardingScreen(rememberNavController())
}
