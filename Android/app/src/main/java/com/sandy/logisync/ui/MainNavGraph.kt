package com.sandy.logisync.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.core.navigation.Route
import com.feature.admin.AdminScreen
import com.feature.admin.details.UserDetailsScreen
import com.feature.arrest.ArrestScreen
import com.feature.arrest.admin.ArrestAdminDetailsScreen
import com.feature.arrest.admin.ArrestAdminScreen
import com.feature.arrest.details.ArrestDetailsScreen
import com.feature.home.HomeScreen
import com.feature.login.loginscreen.LoginScreen
import com.feature.onboard.OnboardingScreen
import com.feature.signup.SignupScreen
import com.sandy.statistics.StatisticsScreen
import com.sandy.statistics.admin.StatisticsAdminScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = Route.Login.route,
        ) {
            LoginScreen(
                navController = navController,
                onLogin = { account ->
                    navController.navigate(Route.Onboarding.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                    }
                },
            )
        }
        composable(
            route = Route.Signup.route,
        ) {
            SignupScreen(
                navController = navController,
            )
        }
        composable(
            route = Route.Onboarding.route,
        ) {
            OnboardingScreen(
                onNavigate = {
                    navController.navigate(Route.Home.route) {
                        popUpTo(Route.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Route.Home.route,
        ) {
            HomeScreen(
                navController = navController,
            )
        }

        composable(
            route = Route.Statistics.route,
        ) {
            StatisticsScreen(
                navController = navController,
            )
        }

        composable(
            route = Route.Admin.route,
        ) {
            AdminScreen(
                navController = navController,
            )
        }

        composable(
            route = Route.Arrest.route,
        ) {
            ArrestScreen(
                navController = navController,
            )
        }

        composable(
            route = Route.ArrestDetails.route,
        ) {
            ArrestDetailsScreen(
                navController = navController,
            )
        }

        composable(
            route = Route.ArrestAdmin.route,
        ) {
            ArrestAdminScreen(
                navController = navController
            )
        }

        composable(
            route = Route.UserDetails.route,
        ) {
            UserDetailsScreen(
                navController = navController
            )
        }

        composable(
            route = Route.StatisticsAdmin.route,
        ) {
            StatisticsAdminScreen(
                navController = navController
            )
        }

        composable(
            route = Route.ArrestAdminDetails.route,
        ) {
            ArrestAdminDetailsScreen(
                navController = navController
            )
        }

    }
}
