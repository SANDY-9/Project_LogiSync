package com.feature.onboard

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.desinsystem.common.NextButton
import com.feature.onboard.components.AppConnect
import com.feature.onboard.components.BluetoothConnect
import com.feature.onboard.components.WatchPairingCheck
import com.feature.onboard.model.OnboardPhase
import com.feature.onboard.model.OnboardUiState

@Composable
fun OnboardingScreen(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current as? Activity?
    LaunchedEffect(state.phase) {
        if(state.phase == OnboardPhase.SERVICE_START) {
            onNavigate()
        }
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OnboardingAppBar(
            onNavigate = { context?.finish() }
        )
        OnboardingContent(
            state = state,
            onNext = viewModel::updatePhase,
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
private fun OnboardingAppBar(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        IconButton(
            modifier = modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterStart),
            onClick = onNavigate
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun OnboardingContent(
    state: OnboardUiState,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.onboard_connect_title1),
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = stringResource(id = R.string.onboard_connect_title2),
            style = MaterialTheme.typography.headlineSmall,
        )

        when (state.phase) {
            OnboardPhase.BLUETOOTH_CONNECT -> BluetoothConnect(state.bluetoothState)
            OnboardPhase.WATCH_PAIRING_CHECK -> WatchPairingCheck(state.isBondedWatch)
            else -> AppConnect(state.isConnectedApp)
        }
    }

    if (state.enableServiceStart) {
        NextButton(
            title = stringResource(id = R.string.onboard_button_complete),
            onClick = onNext,
        )
    }
    else {
        NextButton(
            title = stringResource(id = R.string.onboard_button),
            enabled = state.enabledNextButton,
            onClick = onNext,
        )
    }
}
