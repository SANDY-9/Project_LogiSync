package com.core.model

import com.core.model.Member.Duty
import com.core.model.Member.Team

data class Account(
    val id: String = "",
    val name: String = "홍길동",
    val tel: String = "",
    val duty: Duty = Duty.NORMAL,
    val team: Team,
)
