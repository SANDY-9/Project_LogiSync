package com.feature.onboard.model

import androidx.compose.runtime.Stable
import com.core.domain.enums.BluetoothState

@Stable
internal data class OnboardUiState(
    val phase: OnboardPhase = OnboardPhase.BLUETOOTH_CONNECT,
    val enabledNextButton: Boolean = false,
    val bluetoothState: BluetoothState = BluetoothState.NONE,
    val isBondedWatch: Boolean = false,
    val isConnectedApp: Boolean = false,
    val loading: Boolean = false
)

internal enum class OnboardPhase {
    BLUETOOTH_CONNECT, APP_CONNECTION, ENABLE_SERVICE_START, SERVICE_START;

    fun nextPhase(): OnboardPhase {
        val nextPhase = when (this) {
            BLUETOOTH_CONNECT -> APP_CONNECTION
            APP_CONNECTION -> ENABLE_SERVICE_START
            else -> SERVICE_START
        }
        return nextPhase
    }
}
