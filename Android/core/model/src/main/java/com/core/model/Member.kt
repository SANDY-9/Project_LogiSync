package com.core.model

import java.time.LocalDateTime

data class Member(
    val id: String,
    val name: String,
    val tel: String,
    val duty: Duty,
    val team: Team,
    val lastBpm: Int,
    val lastBpmDateTime: LocalDateTime,
    val minCriticalPoint: Int,
    val maxCriticalPoint: Int,
    val arrestList: List<Arrest>,
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
    enum class Team {
        물류1팀, 물류2팀, 패킹1팀, 패킹2팀, 포장1팀, 포장2팀, TES물류기술연구소
    }
}
