package com.feature.admin.model

import com.core.model.Member
import java.time.LocalDateTime

internal data class AdminUiState(
    val query: String = "",
    val allFilterSelected: Boolean = true,
    val attentionFilterSelected: Boolean = false,
    val dangerFilterSelected: Boolean = false,
    val memberList: Map<Member.Team, List<Member>> = mapOf(
        Member.Team.물류1팀 to List(10) {
            Member(
                id = "",
                name = "홍길동",
                tel = "010-1234-5678",
                duty = Member.Duty.NORMAL,
                team = Member.Team.물류1팀,
                lastBpm = 88,
                lastBpmDateTime = LocalDateTime.now(),
                minCriticalPoint = 20,
                maxCriticalPoint = 130,
                arrestList = emptyList(),
            )
        },
        Member.Team.물류2팀 to List(10) {
            Member(
                id = "",
                name = "홍길동",
                tel = "010-1234-5678",
                duty = Member.Duty.NORMAL,
                team = Member.Team.물류1팀,
                lastBpm = 88,
                lastBpmDateTime = LocalDateTime.now(),
                minCriticalPoint = 20,
                maxCriticalPoint = 130,
                arrestList = emptyList(),
            )
        },
        Member.Team.TES물류기술연구소 to List(10) {
            Member(
                id = "",
                name = "홍길동",
                tel = "010-1234-5678",
                duty = Member.Duty.NORMAL,
                team = Member.Team.물류1팀,
                lastBpm = 88,
                lastBpmDateTime = LocalDateTime.now(),
                minCriticalPoint = 20,
                maxCriticalPoint = 130,
                arrestList = emptyList(),
            )
        }
    )
)
