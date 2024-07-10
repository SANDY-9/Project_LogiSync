package com.feature.onboard.model

internal sealed interface OnboardUiEvent {

    // Navigate
    data object NavigateToNextPhase : OnboardUiEvent
}
