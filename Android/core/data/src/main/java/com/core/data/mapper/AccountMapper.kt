package com.core.data.mapper

import com.core.model.Account
import com.core.model.User
import com.sandy.datastore.model.AccountDTO

internal fun AccountDTO.toAccount(): Account {
    return Account(
        id = id,
        name = name,
        tel = tel,
        duty = User.Duty.valueOf(duty),
        team = User.Team.valueOf(team)
    )
}
