package com.core.firebase.mappers

import com.core.firebase.model.AccountDTO
import com.core.model.Account
import com.core.model.Member

internal fun AccountDTO.toAccount(id: String): Account {
    return Account(
        id = id,
        name = name,
        tel = tel,
        duty = Member.Duty.valueOf(duty),
        team = Member.Team.valueOf(team)
    )
}
