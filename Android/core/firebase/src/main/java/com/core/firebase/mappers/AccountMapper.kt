package com.core.firebase.mappers

import com.core.firebase.model.AccountDTO
import com.core.model.Account
import com.core.model.User

internal fun AccountDTO.toAccount(id: String): Account {
    return Account(
        id = id,
        name = name,
        tel = tel,
        duty = User.Duty.valueOf(duty),
        team = User.Team.valueOf(team)
    )
}
