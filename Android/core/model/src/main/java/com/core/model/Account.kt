package com.core.model

data class Account(
    val id: String = "",
    val name: String = "",
    val tel: String = "",
    val duty: Duty = Duty.NORMAL,
) {
    enum class Duty {
        ADMIN,
        NORMAL;

        fun Duty.name(): String {
            return when (this) {
                ADMIN -> "관리자"
                else -> "일반 사원"
            }
        }
    }
}
