package com.feature.staff

import StaffSearchFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import com.core.desinsystem.lottie.LottieProgressBarBlue
import com.feature.staff.components.StaffList
import com.feature.staff.components.StaffListPage
import com.feature.staff.components.StaffSearchField

@Composable
fun StaffListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: StaffListViewModel = hiltViewModel()
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .addFocusCleaner(focusManager),
    ) {
        ArrestAppBar(
            onNavigateUp = navController::navigateUp,
        )
        StaffSearchField(
            query = state.query,
            onQueryChange = viewModel::inputQuery,
            onQueryClear = viewModel::clearQuery,
            onSearch = viewModel::requestSearch,
            lazyListState = lazyListState,
            focusManager = focusManager,
        )
        Spacer(modifier = modifier.height(12.dp))
        StaffSearchFilter(
            filterExpand = state.filterExpand,
            onFilterExpand = viewModel::expandFilter,
            walkSortSelect = state.walkSortSelect,
            walkDistanceSortSelect = state.walkDistanceSortSelect,
            heartRateSortSelect = state.heartRateSortSelect,
            walkFilterSelect = state.walkFilterSelect,
            walkDistanceFilterSelect = state.walkDistanceFilterSelect,
            heartRateFilterSelect = state.heartRateFilterSelect,
            onFilterSelect = viewModel::selectFilter,
            onFilterApply = viewModel::applyFilter,
            onFilterInit = viewModel::initFilter,
            pagingFilterVisible = state.pagingFilterVisible,
            onPagingFilterVisibleChange = viewModel::changePagingFilter,
            selectPaging = state.paging,
            lazyListState = lazyListState,
            onPagingSelected = viewModel::selectPaging,
        )
        Spacer(modifier = modifier.height(8.dp))
        if(state.loading) {
            LottieProgressBarBlue(modifier = modifier.fillMaxSize())
        }
        else {
            StaffList(
                staffList = state.filteredStaffListCurrentPage,
                state = lazyListState,
                modifier = modifier.weight(1f),
            )
            StaffListPage(
                currentPage = state.currentPage,
                currentPages = state.currentPages,
                isFirstPage = state.isFirstPage,
                isEndPage = state.isEndPage,
                pageCount = state.currentPages.size,
                onPrevPage = viewModel::prevPage,
                onFirstPage = viewModel::firstPage,
                onNextPage = viewModel::nextPage,
                onEndPage = viewModel::endPage,
                onPageSelect = viewModel::selectPage,
            )
        }
        Spacer(modifier = modifier.height(20.dp))
    }
}

@Composable
private fun ArrestAppBar(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 5.dp, end = 13.dp)
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = modifier,
            onClick = onNavigateUp,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
        }
        Text(
            text = stringResource(id = R.string.staff_list_title),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview(name = "StaffListScreen")
@Composable
private fun PreviewStaffListScreen() {
    StaffListScreen(rememberNavController())
}
