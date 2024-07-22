package com.feature.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.addFocusCleaner
import com.core.navigation.Args
import com.core.navigation.Route
import com.feature.admin.components.UserFilter
import com.feature.admin.components.UserList
import com.feature.admin.components.UserSearchField

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
            .addFocusCleaner(focusManager)
    ) {
        AdminAppBar()
        Spacer(modifier = modifier.height(4.dp))
        UserSearchField(
            query = state.query,
            onQueryChange = viewModel::inputQuery,
            onQueryClear = viewModel::clearQuery,
            onSearch = viewModel::requestSearch,
            focusManager = focusManager,
        )
        UserFilter(
            allFilterSelected = state.allFilterSelected,
            onSelectAllFilter = viewModel::getAllMemberList,
            dangerFilterSelected = state.dangerFilterSelected,
            onSelectDangerFilter = viewModel::getDangerMemberList,
            onRefreshList = viewModel::refreshMemberList,
        )
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

@Composable
private fun AdminAppBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            modifier = modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.admin_menu_title),
            style = MaterialTheme.typography.headlineSmall,
        )

        Spacer(modifier = modifier.weight(1f))

        IconButton(
            modifier = modifier
                .padding(end = 4.dp, top = 8.dp),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = null
            )
        }
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
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            tint = Color.Gray,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.admin_search_result_empty),
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray
        )
    }
}

@Preview(name = "AdminScreen")
@Composable
private fun PreviewAdminScreen() {
    AdminScreen(rememberNavController())
}
