package com.feature.login.loginscreen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(
    composeNavigator: NavController,
    modifier: Modifier = Modifier,
    //viewModel: LoginViewModel = hiltViewModel(),
) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "테스트")
    }
}

@Composable
@Preview(
    name = "LoginScreen",
    showBackground = true,
    showSystemUi = true
)
private fun LoginScreenPreview() {
    LoginScreen(
        rememberNavController()
    )
}
