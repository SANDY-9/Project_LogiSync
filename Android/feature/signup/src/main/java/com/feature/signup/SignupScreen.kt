package com.feature.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SignupScreen(
    state: SignupState,
    actions: SignupActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Signup")
private fun SignupScreenPreview() {
    SignupScreen(
        state = SignupState(),
        actions = SignupActions()
    )
}
