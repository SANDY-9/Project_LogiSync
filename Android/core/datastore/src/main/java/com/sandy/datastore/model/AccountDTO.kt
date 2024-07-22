package com.sandy.datastore.model

import com.core.model.User

data class AccountDTO(
    val id: String,
    val name: String,
    val tel: String,
    val duty: String = User.Duty.NORMAL.name,
    val team: String = User.Team.entries.random().name,
)
