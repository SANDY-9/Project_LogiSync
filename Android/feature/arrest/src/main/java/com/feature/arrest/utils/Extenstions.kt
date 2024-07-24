package com.feature.arrest.utils

import com.core.model.Arrest
import com.feature.arrest.model.ArrestUiState
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

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
    return result.toSortedMap(compareByDescending { it })
}

internal fun Long?.localDate() : LocalDate? {
    return this?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
}

internal fun Map<LocalDate, List<Arrest>>.filter(
    type: ArrestUiState.FilterType,
    startDate: LocalDate,
    endDate: LocalDate,
): Map<LocalDate, List<Arrest>> {
    val result = mutableMapOf<LocalDate, List<Arrest>>()
    forEach { (date, list) ->
        val filteredList = list.filter {
            val isWithinDateRange = it.time.toLocalDate() in startDate..endDate
            when(type) {
                ArrestUiState.FilterType.FILTER_ALL -> isWithinDateRange
                ArrestUiState.FilterType.FILTER_DANGER -> {
                    it.arrestType == Arrest.ArrestType.NORMAL && isWithinDateRange
                }
                ArrestUiState.FilterType.FILTER_HEART_RATE -> {
                    it.arrestType != Arrest.ArrestType.NORMAL && isWithinDateRange
                }
            }
        }
        if(filteredList.isNotEmpty()) {
            result[date] = filteredList
        }
    }
    return result.toSortedMap(compareByDescending { it })
}

internal fun Map<LocalDate, List<Arrest>>.filter(id: String): Map<LocalDate, List<Arrest>> {
    val result = mutableListOf<Arrest>()
    forEach { (date, list) ->
        list.forEach {
            if(it.id == id) result.add(it)
        }
    }
    return result.groupBy { it.time.toLocalDate() }.toSortedMap(compareByDescending { it })
}
