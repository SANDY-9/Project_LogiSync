package com.core.firebase

import com.core.model.Account

interface UserDataSource {

    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: String,
        onSuccess: (Boolean) -> Unit,
    )

    fun checkTel(
        tel: String,
        onExisted: (Boolean) -> Unit,
    )

    fun checkId(
        id: String,
        onExisted: (Boolean) -> Unit,
    )

    fun login(
        id: String,
        pwd: String,
        onLogin: (Account) -> Unit,
    )
}
