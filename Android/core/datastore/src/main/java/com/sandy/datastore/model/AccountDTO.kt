package com.sandy.datastore.model

import com.core.model.Member

data class AccountDTO(
    val id: String,
    val name: String,
    val tel: String,
    val duty: String = Member.Duty.NORMAL.name,
    val team: String = Member.Team.entries.random().name,
)
