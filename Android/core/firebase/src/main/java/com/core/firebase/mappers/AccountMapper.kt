package com.core.firebase.mappers

import com.core.firebase.model.AccountDTO
import com.core.model.Account

internal fun AccountDTO.toAccount(id: String): Account {
    return Account(
        id = id,
        name = name,
        tel = tel,
        duty = Account.Duty.valueOf(duty),
    )
}
