package com.core.firebase.model

import com.core.model.Member

internal data class AccountDTO(
    val token: String? = null,
    val pwd: String = "",
    val name: String = "",
    val tel: String = "",
    val duty: String = Member.Duty.NORMAL.name,
    val team: String = Member.Team.entries.random().name,
)
