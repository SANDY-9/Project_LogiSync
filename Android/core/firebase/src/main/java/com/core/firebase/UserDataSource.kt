package com.core.firebase

import com.core.firebase.model.UserDTO

interface UserDataSource {

    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: String,
        onSuccess: (Boolean) -> Unit,
    )

    fun checkSignup(
        tel: String,
        onExisted: (Boolean) -> Unit
    )

    fun checkId(
        id: String,
        onExisted: (Boolean) -> Unit,
    )

    fun login(
        id: String,
        onLogin: (UserDTO) -> Unit,
    )
}
