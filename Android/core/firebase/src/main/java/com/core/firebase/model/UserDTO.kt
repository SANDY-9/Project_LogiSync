package com.core.firebase.model


data class UserDTO(
    val id: String? = null,
    val name: String? = null,
    val tel: String? = null,
    val duty: String? = null,
    val team: String? = null,
    val lastBpm: Int? = null,
    val lastBpmDateTime: String? = null,
    val criticalPoint: CriticalPointDTO? = null
)

data class CriticalPointDTO(
    val max_heart_rate: Int = 0,
    val min_heart_rate: Int = 0,
)
