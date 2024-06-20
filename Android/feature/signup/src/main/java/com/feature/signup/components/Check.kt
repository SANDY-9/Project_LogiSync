package com.feature.signup.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

// 회원가입 여부 확인
@Composable
fun Check(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
    }
}

@Preview(name = "Check")
@Composable
private fun PreviewCheck() {
    Check()
}
