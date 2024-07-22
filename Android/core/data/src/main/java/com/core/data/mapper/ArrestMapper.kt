package com.core.data.mapper

import com.core.firebase.model.ArrestDTO
import com.core.model.Arrest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun Map<String, ArrestDTO>.toArrestList(): List<Arrest> {
    return this.map { (date, dto) ->
        Arrest(
            id = dto.id,
            arrestType = Arrest.ArrestType.valueOf(dto.arrestType),
            time = LocalDateTime.parse(date, dateTimeFormatter),
            lat = dto.lat,
            lng = dto.lng,
            bpm = dto.bpm,
        )
    }.sortedByDescending { it.time }
}

internal fun Map<String, ArrestDTO>.toArrestMap(): Map<LocalDate, List<Arrest>> {
    val result = mutableMapOf<LocalDate, MutableList<Arrest>>()
    // 각 항목을 날짜별로 그룹화
    forEach { (localDateTime, arrest) ->
        val dateTime = LocalDate.parse(localDateTime, dateTimeFormatter)
        result.computeIfAbsent(dateTime) { mutableListOf() }.add(
            Arrest(
                id = arrest.id,
                arrestType = Arrest.ArrestType.valueOf(arrest.arrestType),
                time = LocalDateTime.parse(localDateTime, dateTimeFormatter),
                lat = arrest.lat,
                lng = arrest.lng,
                bpm = arrest.bpm,
            )
        )
    }
    result.forEach { (_, list) ->
        list.sortByDescending { it.time }
    }
    return result
}

private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")
private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
