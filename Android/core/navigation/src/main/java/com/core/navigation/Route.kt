package com.core.navigation

sealed class Route(
    val route: String
) {
    data object Login : Route(route = Screen.LOGIN.name)
    data object Signup : Route(route = Screen.SIGNUP.name)
}

enum class Screen {
    LOGIN, SIGNUP
}
