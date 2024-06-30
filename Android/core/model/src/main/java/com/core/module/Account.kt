package com.core.module

data class Account(
    val id: String,
    val pwd: String,
    val name: String,
    val tel: String,
    val grade: Grade,
) {
    enum class Grade {
        USER,
        ADMIN,
    }
}
