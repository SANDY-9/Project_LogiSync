package com.core.navigation

sealed class Route(
    val route: String
) {
    data object Login : Route(route = Screen.LOGIN.name)
    data object Signup : Route(route = Screen.SIGNUP.name)
    data object Onboarding : Route(route = Screen.ONBOARDING.name)
    data object Home : Route(route = Screen.HOME.name)
    data object Statistics : Route(route = Screen.STATISTICS.name)
    data object Admin : Route(route = Screen.ADMIN.name)
    data object Arrest : Route(route = Screen.ARREST.name)
    data object ArrestDetails : Route(route = Screen.ARREST_DETAILS.name)
    data object UserDetails : Route(route = Screen.USER_DETAILS.name)
    data object Other : Route(route = Screen.OTHER.name)
}

enum class Screen {
    LOGIN,
    SIGNUP,
    ONBOARDING,
    HOME,
    STATISTICS,
    ADMIN,
    ARREST,
    ARREST_DETAILS,
    USER_DETAILS,
    OTHER
}
