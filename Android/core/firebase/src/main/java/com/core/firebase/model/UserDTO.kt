package com.core.firebase.model


data class UserDTO(
    val name: String = "",
    val tel: String = "",
    val duty: String = "",
    val team: String = "",
    val lastBpm: Int? = null,
    val lastBpmDateTime: String? = null,
    val criticalPoint: CriticalPointDTO? = null
)

data class CriticalPointDTO(
    val max_heart_rate: Int = 0,
    val min_heart_rate: Int = 0,
)
