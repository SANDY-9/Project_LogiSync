package com.sandy.logisync.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.core.navigation.Route
import com.feature.login.loginscreen.LoginScreen
import com.feature.signup.SignupScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = Route.Login.route,
        ) {
            LoginScreen(
                navController = navController,
                modifier = modifier
            )
        }
        composable(
            route = Route.Signup.route,
        ) {
            SignupScreen(
                navController = navController
            )
        }

    }
}
