package com.core.data.mapper

import com.core.firebase.model.ArrestDTO
import com.core.model.Arrest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun Map<String, ArrestDTO>.toArrestList(): List<Arrest> {
    return this.map { (date, dto) ->
        Arrest(
            id = dto.id,
            time = LocalDateTime.parse(date, formatter),
            lat = dto.lat,
            lng = dto.lng,
            arrestType = Arrest.ArrestType.valueOf(dto.arrestType),
        )
    }
}

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")
