package com.core.model

data class Account(
    val id: String = "",
    val name: String = "홍길동",
    val tel: String = "",
    val duty: Duty = Duty.NORMAL,
) {
    enum class Duty {
        ADMIN,
        NORMAL;

        fun str(): String {
            return when (this) {
                ADMIN -> "관리자"
                else -> "사원"
            }
        }
    }
}
