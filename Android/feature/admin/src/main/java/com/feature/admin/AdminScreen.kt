package com.feature.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.feature.admin.components.MemberFilter
import com.feature.admin.components.MemberList
import com.feature.admin.components.MemberSearchField

@Composable
fun AdminScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AdminViewModel = hiltViewModel(),
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AdminAppBar()
        Spacer(modifier = modifier.height(4.dp))
        MemberSearchField(
            query = state.query,
            onQueryChange = viewModel::inputQuery,
            onQueryClear = viewModel::clearQuery,
            onSearch = viewModel::requestSearch,
        )
        MemberFilter(
            allFilterSelected = state.allFilterSelected,
            onSelectAllFilter = viewModel::getAllMemberList,
            attentionFilterSelected = state.attentionFilterSelected,
            onSelectAttentionFilter = viewModel::getAttentionMemberList,
            dangerFilterSelected = state.dangerFilterSelected,
            onSelectDangerFilter = viewModel::getDangerMemberList,
            modifier = Modifier,
        )
        MemberList(
            memberList = state.memberList,
            onItemClick = { member ->

            }
        )
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

@Preview(name = "AdminScreen")
@Composable
private fun PreviewAdminScreen() {
    AdminScreen(rememberNavController())
}
