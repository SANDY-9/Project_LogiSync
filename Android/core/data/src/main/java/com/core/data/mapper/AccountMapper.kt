package com.core.data.mapper

import com.core.model.Account
import com.core.model.Member
import com.sandy.datastore.model.AccountDTO

internal fun AccountDTO.toAccount(): Account {
    return Account(
        id = id,
        name = name,
        tel = tel,
        duty = Member.Duty.valueOf(duty),
        team = Member.Team.valueOf(team)
    )
}
