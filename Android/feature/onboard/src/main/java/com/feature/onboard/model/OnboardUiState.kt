package com.feature.onboard.model

import androidx.compose.runtime.Stable
import com.sandy.bluetooth.BluetoothState

@Stable
internal data class OnboardUiState(
    val phase: OnboardPhase = OnboardPhase.BLUETOOTH_CONNECT,
    val enabledNextButton: Boolean = false,
    val bluetoothState: BluetoothState = BluetoothState.NONE,
    val isBondedWatch: Boolean = false,
    val isConnectedApp: Boolean = false,
    val isServiceStarted: Boolean = false,
)

internal enum class OnboardPhase {
    BLUETOOTH_CONNECT, WATCH_PAIRING_CHECK, APP_CONNECTION;

    fun nextPhase(): OnboardPhase {
        val nextPhase = when (this) {
            BLUETOOTH_CONNECT -> WATCH_PAIRING_CHECK
            else -> APP_CONNECTION
        }
        return nextPhase
    }
}
