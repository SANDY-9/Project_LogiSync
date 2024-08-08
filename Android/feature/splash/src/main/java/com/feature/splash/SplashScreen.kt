package com.feature.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashScreen(
    onLogin: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
) {

    val isLogin by viewModel.account.collectAsStateWithLifecycle()
    val splashShow by viewModel.splashShow.collectAsStateWithLifecycle()

    LaunchedEffect(splashShow) {
        if (!splashShow) onLogin(isLogin)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = splashShow,
            enter = fadeIn(
                animationSpec = tween(500)
            ),
            exit = fadeOut(
                animationSpec = tween(500)
            )
        ) {
            Image(
                painter = painterResource(id = com.core.desinsystem.R.drawable.temp_logo),
                contentDescription = null
            )
        }
    }
}
