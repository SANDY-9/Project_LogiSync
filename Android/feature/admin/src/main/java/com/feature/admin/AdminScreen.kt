package com.feature.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.MySearchTextField
import com.core.desinsystem.common.NetworkError
import com.core.desinsystem.common.addFocusCleaner
import com.core.desinsystem.lottie.LottieProgressBarBlue
import com.core.desinsystem.theme.LogiBlue
import com.core.navigation.Args
import com.core.navigation.Route
import com.feature.admin.components.UserFilter
import com.feature.admin.components.UserList

@Composable
fun AdminScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AdminViewModel = hiltViewModel(),
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .addFocusCleaner(focusManager)
    ) {
        AdminAppBar(
            onNavigateStaff = { navController.navigate(Route.StaffList.route ) },
            onNavigateArrest = { navController.navigate(Route.ArrestAdmin.route) },
        )
        if(state.error != null) NetworkError(modifier = modifier.fillMaxSize())
        Spacer(modifier = modifier.height(4.dp))
        MySearchTextField(
            query = state.query,
            onQueryChange = viewModel::inputQuery,
            onQueryClear = viewModel::clearQuery,
            onSearch = viewModel::requestSearch,
            focusManager = focusManager,
            hint = stringResource(id = R.string.admin_search_hint)
        )
        UserFilter(
            allFilterSelected = state.allFilterSelected,
            onSelectAllFilter = viewModel::getAllMemberList,
            heartRateFilterSelected = state.heartFilterSelected,
            onSelectHeartRateFilter = viewModel::getHeartRateMemberList,
            dangerFilterSelected = state.dangerFilterSelected,
            onSelectDangerFilter = viewModel::getDangerMemberList,
            onRefreshList = viewModel::refreshMemberList,
        )
        if(state.loading) {
            LottieProgressBarBlue(modifier = modifier
                .fillMaxSize()
                .weight(1f))
        }
        else {
            if(state.filteredUserList.isEmpty()) {
                EmptyUser()
            }
            else {
                UserList(
                    userList = state.filteredUserList,
                    onItemClick = { user ->
                        navController.run {
                            currentBackStackEntry?.savedStateHandle?.set(Args.USER, user)
                            navigate(Route.UserDetails.route)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun AdminAppBar(
    onNavigateStaff: () -> Unit,
    onNavigateArrest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            modifier = modifier.padding(start = 20.dp),
            text = stringResource(id = R.string.admin_menu_title),
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = modifier.weight(1f))

        Icon(
            modifier = modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable(onClick = onNavigateStaff)
                .padding(5.dp),
            imageVector = Icons.Rounded.Add,
            tint = LogiBlue,
            contentDescription = null
        )
        Icon(
            modifier = modifier
                .padding(end = 13.dp)
                .size(35.dp)
                .clip(CircleShape)
                .clickable(onClick = onNavigateArrest)
                .padding(5.dp),
            imageVector = Icons.Rounded.Notifications,
            tint = LogiBlue,
            contentDescription = null
        )
    }
}

@Composable
private fun EmptyUser(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = modifier.size(20.dp),
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            tint = Color.Gray,
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.admin_search_result_empty),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Preview(name = "AdminScreen")
@Composable
private fun PreviewAdminScreen() {
    AdminScreen(rememberNavController())
}
