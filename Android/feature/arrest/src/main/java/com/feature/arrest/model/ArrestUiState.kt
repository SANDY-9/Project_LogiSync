package com.feature.arrest.model

import com.core.model.Arrest
import java.time.LocalDate

internal data class ArrestUiState(
    val arrestList: Map<LocalDate, List<Arrest>> = emptyMap(),
    val filteredList: Map<LocalDate, List<Arrest>> = emptyMap(),
    val allFilterSelected: Boolean = true,
    val dangerFilterSelected: Boolean = false,
    val heartRateFilterSelected: Boolean = false,
) {
    enum class FilterType {
        FILTER_ALL, FILTER_DANGER, FILTER_HEART_RATE
    }
}
