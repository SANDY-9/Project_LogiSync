package com.feature.onboard.model

import androidx.compose.runtime.Stable
import com.sandy.bluetooth.BluetoothState

@Stable
internal data class OnboardUiState(
    val phase: OnboardPhase = OnboardPhase.BLUETOOTH_CONNECT,
    val enabledNextButton: Boolean = false,
    val bluetoothState: BluetoothState = BluetoothState.NONE,
)

enum class OnboardPhase {
    BLUETOOTH_CONNECT, WATCH_CONNECT, WEAR_APP_INSTALL
}
