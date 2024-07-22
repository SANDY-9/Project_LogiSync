package com.core.firebase.model

import com.core.model.User

internal data class AccountDTO(
    val token: String? = null,
    val pwd: String = "",
    val name: String = "",
    val tel: String = "",
    val duty: String = User.Duty.NORMAL.name,
    val team: String = User.Team.entries.random().name,
)
