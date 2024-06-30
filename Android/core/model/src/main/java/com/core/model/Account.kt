package com.core.model

data class Account(
    val id: String,
    val pwd: String,
    val name: String,
    val tel: String,
    val duty: Duty,
) {
    enum class Duty {
        ADMIN,
        NORMAL,
    }
}
