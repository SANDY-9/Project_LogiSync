package com.feature.arrest.utils

import com.core.model.Arrest
import com.feature.arrest.model.ArrestUiState
import java.time.LocalDate

internal fun Map<LocalDate, List<Arrest>>.filter(type: ArrestUiState.FilterType): Map<LocalDate, List<Arrest>> {
    val result = this.toMutableMap()
    forEach { (date, list) ->
        result[date] = list.filter {
            if(it.arrestType == Arrest.ArrestType.NORMAL) {
                type == ArrestUiState.FilterType.FILTER_DANGER
            }
            else {
                type == ArrestUiState.FilterType.FILTER_HEART_RATE
            }
        }
    }
    return result
}
