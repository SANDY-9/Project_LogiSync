package com.feature.admin.model

import com.core.model.User

internal data class AdminUiState(
    val loading: Boolean = false,
    val query: String = "",
    val allFilterSelected: Boolean = true,
    val dangerFilterSelected: Boolean = false,
    val heartFilterSelected: Boolean = false,
    val userList: Map<User.Team, List<User>> = emptyMap(),
    val searchUserList: Map<User.Team, List<User>> = emptyMap(),
    val filteredUserList: Map<User.Team, List<User>> = emptyMap(),
    val error: Throwable? = null
)
