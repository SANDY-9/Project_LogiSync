package com.core.data.mapper

import com.core.firebase.model.UserDTO
import com.core.model.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun UserDTO.toUser(id: String): User {
    return User(
        id = id,
        name = name ?: "",
        tel = tel ?: "",
        duty = duty?.let { User.Duty.valueOf(it) } ?: User.Duty.NORMAL,
        team = team?.let { User.Team.valueOf(it) } ?: User.Team.물류1팀,
        lastBpm = lastBpm,
        lastBpmDateTime = lastBpmDateTime?.let { LocalDateTime.parse(lastBpmDateTime, formatter) },
        minCriticalPoint = criticalPoint?.min_heart_rate,
        maxCriticalPoint = criticalPoint?.max_heart_rate,
    )
}

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")

internal fun List<UserDTO?>.toUserMap(): Map<User.Team, List<User>> {
    return mapNotNull { dto ->
        // UserDTO를 User로 변환
        dto?.toUser(dto.id ?: "")
    }.groupBy { user ->
        // team을 기준으로 그룹화
        user.team
    }
}
