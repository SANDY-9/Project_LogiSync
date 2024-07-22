package com.core.data.mapper

import com.core.firebase.model.UserDTO
import com.core.model.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun UserDTO.toUser(id: String): User {
    return User(
        id = id,
        name = name,
        tel = tel,
        duty = User.Duty.valueOf(duty),
        team = User.Team.valueOf(team),
        lastBpm = lastBpm,
        lastBpmDateTime = lastBpmDateTime?.let { LocalDateTime.parse(lastBpmDateTime, formatter) },
        minCriticalPoint = criticalPoint?.min_heart_rate,
        maxCriticalPoint = criticalPoint?.max_heart_rate,
    )
}

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")
