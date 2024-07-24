package com.sandy.logisync.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.icons.AdminOff
import com.core.desinsystem.icons.AdminOn
import com.core.desinsystem.icons.HeartOff
import com.core.desinsystem.icons.HeartOn
import com.core.desinsystem.icons.More
import com.core.model.User
import com.core.navigation.Route
import com.feature.other.OtherActivity
import com.sandy.logisync.R

@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val duty by viewModel.duty.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val currentRoute = navBackStackEntry?.destination?.route
            MainNavGraph(
                modifier = modifier
                    .fillMaxSize()
                    .weight(1f),
                navController = navController,
                startDestination = Route.Login.route,
                //startDestination = Route.Home.route,
            )
            if(currentRoute in BottomNavRouteEntry) {
                BottomNavigationBar(
                    // 관리자인지 아닌지 판별
                    items = if(duty == User.Duty.ADMIN) TOP_LEVEL_DESTINATIONS_ADMIN else TOP_LEVEL_DESTINATIONS_NORMAL,
                    currentRoute = currentRoute,
                    onItemClick = {
                        if (currentRoute != it.route) {
                            if (it.route == Route.Other.route) {
                                navigateToOtherScreen(context)
                            }
                            else {
                            navController.navigate(it.route)
                                }
                        }
                    },
                )
            }
        }
    }
}

private fun navigateToOtherScreen(context: Context) {
    val intent = Intent(context, OtherActivity::class.java)
    context.startActivity(intent)
}

private val BottomNavRouteEntry = listOf(
    Route.Home.route,
    Route.Statistics.route,
    Route.Arrest.route,
    Route.Admin.route,
)

@Composable
private fun BottomNavigationBar(
    items: List<BottomNavItem>,
    currentRoute: String?,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomNavigation (
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        // items 배열에 담긴 모든 항목을 추가합니다.
        items.forEach { item ->
            // 뷰의 활동 상태를 백스택에 담아 저장합니다.
            val selected = item.route == currentRoute
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.DarkGray,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        // 뱃지카운트가 1이상이면, 아이콘에 뱃지카운트가 표시됩니다.
                        // 아이콘이 선택 되었을 때, 아이콘 밑에 텍스트를 표시합니다.
                        if (selected) {
                            Icon(
                                imageVector = item.selectedIcon,
                                contentDescription = null
                            )
                        }
                        else {
                            Icon(
                                imageVector = item.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    }
}

private data class BottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
)

private val TOP_LEVEL_DESTINATIONS_NORMAL = listOf(
    BottomNavItem(
        route = Route.Home.route,
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.bottom_nav_item_home,
    ),
    BottomNavItem(
        route = Route.Statistics.route,
        selectedIcon = Icons.HeartOn,
        unselectedIcon = Icons.HeartOff,
        iconTextId = R.string.bottom_nav_item_statistics,
    ),
    BottomNavItem(
        route = Route.Arrest.route,
        selectedIcon = Icons.Rounded.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        iconTextId = R.string.bottom_nav_item_arrest,
    ),
    BottomNavItem(
        route = Route.Other.route,
        selectedIcon = Icons.More,
        unselectedIcon = Icons.More,
        iconTextId = R.string.bottom_nav_item_other,
    )
)

private val TOP_LEVEL_DESTINATIONS_ADMIN = listOf(
    BottomNavItem(
        route = Route.Home.route,
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.bottom_nav_item_home,
    ),
    BottomNavItem(
        route = Route.Statistics.route,
        selectedIcon = Icons.HeartOn,
        unselectedIcon = Icons.HeartOff,
        iconTextId = R.string.bottom_nav_item_statistics,
    ),
    BottomNavItem(
        route = Route.Arrest.route,
        selectedIcon = Icons.Rounded.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        iconTextId = R.string.bottom_nav_item_arrest,
    ),
    BottomNavItem(
        route = Route.Admin.route,
        selectedIcon = Icons.AdminOn,
        unselectedIcon = Icons.AdminOff,
        iconTextId = R.string.bottom_nav_item_admin,
    ),
    BottomNavItem(
        route = Route.Other.route,
        selectedIcon = Icons.More,
        unselectedIcon = Icons.More,
        iconTextId = R.string.bottom_nav_item_other,
    )
)

@Preview(name = "MainScreen")
@Composable
private fun PreviewMainScreen() {
    MainScreen(rememberNavController())
}
