package com.feature.admin.utils

import com.core.model.User

internal fun Map<User.Team, List<User>>.filter(query: String): Map<User.Team, List<User>> {
    val upper = query.uppercase()
    val filteredUsers = flatMap { (team, users) ->
        users.filter { user ->
            team.name.uppercase().contains(upper) || user.name.uppercase().contains(upper) || user.id == query
        }
    }
    return filteredUsers.groupBy { it.team }
}

internal fun Map<User.Team, List<User>>.filterArrest(): Map<User.Team, List<User>> {
    val filteredUsers = flatMap { (team, users) ->
        users.filter { user ->
            if(user.lastBpm != null && user.maxCriticalPoint != null && user.minCriticalPoint != null) {
                user.lastBpm!! > user.maxCriticalPoint!! || user.lastBpm!! < user.minCriticalPoint!!
            } else false
        }
    }
    return filteredUsers.groupBy { it.team }
}

internal fun Map<User.Team, List<User>>.filterHeart(): Map<User.Team, List<User>> {
    val filteredUsers = flatMap { (team, users) ->
        users.filter { user ->
            user.lastBpm != null
        }
    }
    return filteredUsers.groupBy { it.team }
}
