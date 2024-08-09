package com.feature.stafflist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.desinsystem.theme.LogiBlue
import com.feature.stafflist.components.StaffList
import com.feature.stafflist.components.StaffListPage
import com.feature.stafflist.components.StaffListPageDropDown
import com.feature.stafflist.components.StaffSearchField
import com.feature.stafflist.components.StaffSearchFilter

@Composable
fun StaffListScreen(
    modifier: Modifier = Modifier,
    viewModel: StaffListViewModel = hiltViewModel()
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        ArrestAppBar()
        StaffSearchField(
            query = state.query,
            onQueryChange = viewModel::inputQuery,
            onQueryClear = viewModel::clearQuery,
            onSearch = viewModel::requestSearch,
            focusManager = focusManager,
        )
        StaffSearchFilter(
            filterExpand = state.filterExpand,
            onFilterExpand = viewModel::expandFilter,
            walkSortSelect = state.walkSortSelect,
            walkDistanceSortSelect = state.walkDistanceSortSelect,
            heartRateSortSelect = state.heartRateSortSelect,
            onWalkSortSelect = { /*TODO*/ },
            onWalkDistanceSortSelect = { /*TODO*/ },
            onHeartRateSortSelect = { /*TODO*/ },
            walkFilterSelect = state.walkFilterSelect,
            walkDistanceFilterSelect = state.walkDistanceFilterSelect,
            heartRateFilterSelect = state.heartRateFilterSelect,
            onWalkFilterSelect = { /*TODO*/ },
            onWalkDistanceFilterSelect = { /*TODO*/ },
            onHeartRateFilterSelect = { /*TODO*/ },
            onFilterApply = { /*TODO*/ },
            onFilterInit = { /*TODO*/ },
        )
        StaffListPageDropDown()
        StaffList(
            staffList = state.staffListPaging,
            modifier = modifier.weight(1f),
        )
        StaffListPage(
            selectPage = state.selectPage,
            currentPages = state.currentPages,
            onPrevPage = { /*TODO*/ },
            onFirstPage = { /*TODO*/ },
            onNextPage = { /*TODO*/ },
            onEndPage = { /*TODO*/ },
            onPageSelect = viewModel::selectPage,
        )
        Spacer(modifier = modifier.height(20.dp))
    }
}

@Composable
private fun ArrestAppBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 13.dp)
    ) {
        Row(
            modifier = modifier
                .height(56.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.staff_list_title),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = modifier.weight(1f))
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
            }
        }
    }
}


@Preview(name = "StaffListScreen")
@Composable
private fun PreviewStaffListScreen() {
    StaffListScreen()
}
