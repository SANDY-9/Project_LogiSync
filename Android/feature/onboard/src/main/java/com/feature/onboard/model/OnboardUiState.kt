package com.feature.onboard.model

import androidx.compose.runtime.Stable
import com.sandy.bluetooth.BluetoothState

@Stable
internal data class OnboardUiState(
    val phase: OnboardPhase = OnboardPhase.BLUETOOTH_CONNECT,
    val enabledNextButton: Boolean = false,
    val bluetoothState: BluetoothState = BluetoothState.NONE,
    val isBondedWatch: Boolean = false
)

internal enum class OnboardPhase {
    BLUETOOTH_CONNECT, WATCH_PAIRING_CHECK, WATCH_CONNECTION, LINK_WEAR_APP;

    fun nextPhase(): OnboardPhase {
        val nextPhase = when (this) {
            BLUETOOTH_CONNECT -> WATCH_PAIRING_CHECK
            WATCH_PAIRING_CHECK -> WATCH_CONNECTION
            else -> LINK_WEAR_APP
        }
        return nextPhase
    }
}
