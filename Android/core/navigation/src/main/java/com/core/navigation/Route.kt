package com.core.navigation

sealed class Route(
    val route: String
) {
    data object Login : Route(route = Screen.LOGIN.name)
    data object Signup : Route(route = Screen.SIGNUP.name)
    data object Onboarding : Route(route = Screen.ONBOARDING.name)
    data object Home : Route(route = Screen.HOME.name)
    data object Statistics : Route(route = Screen.STATISTICS.name)
}

enum class Screen {
    LOGIN,
    SIGNUP,
    ONBOARDING,
    HOME,
    STATISTICS,
}
