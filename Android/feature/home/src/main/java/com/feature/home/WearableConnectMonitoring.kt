package com.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect

@Composable
internal fun StopMonitoringWearableConnect(viewModel: HomeViewModel) {
    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.stopWearableConnectMonitoring()
        }
    }
}

@Composable
internal fun StartMonitoringWearableConnect(viewModel: HomeViewModel) {
    LaunchedEffect(key1 = Unit) {
        viewModel.restartWearableConnectMonitoring()
    }
}
