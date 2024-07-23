package com.feature.arrest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.MyDateRangePickerBottomSheet
import com.core.desinsystem.theme.LogiBlue
import com.core.navigation.Args
import com.core.navigation.Route
import com.core.utils.DateUtil
import com.feature.arrest.components.ArrestFilter
import com.feature.arrest.components.ArrestItem
import com.feature.arrest.components.ArrestStickyHeader
import com.feature.arrest.components.EmptyArrestItem
import com.feature.arrest.model.ArrestUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArrestScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ArrestViewModel = hiltViewModel()
) {

    LaunchedEffect(navController.previousBackStackEntry) {
        val id = navController.previousBackStackEntry?.savedStateHandle?.get<String>(Args.ID)
        viewModel.getArrestList(id)
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        ArrestAppBar(
            allFilterSelected = state.allFilterSelected,
            dangerFilterSelected = state.dangerFilterSelected,
            heartRateFilterSelected = state.heartRateFilterSelected,
            onSelectFilter = viewModel::filterArrestList,
            onRefresh = viewModel::refreshArrestList,
            onOpenDatePicker = viewModel::setDatePickerVisible,
        )

        if(state.filteredList.isEmpty()) {
            EmptyArrestItem()
        }
        else {
            LazyColumn(
                state = lazyListState
            ) {
                state.filteredList.forEach { (date, items) ->
                    stickyHeader {
                        ArrestStickyHeader(
                            date = DateUtil.convertDate(date),
                            count = items.size
                        )
                    }
                    items(
                        items = items,
                        key = { it.time }
                    ) { arrest ->
                        ArrestItem(
                            arrest = arrest,
                            onItemClick = {
                                navController.run {
                                    currentBackStackEntry?.savedStateHandle?.set(Args.ARREST, arrest)
                                    navigate(Route.ArrestDetails.route)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    if (state.datePickerVisible) {
        MyDateRangePickerBottomSheet(
            selectedStartDateStr = state.selectedStartDateStr,
            selectedEndDateStr = state.selectedEndDateStr,
            onSelectedStartDate = viewModel::selectedStartDate,
            onSelectedEndDate = viewModel::selectedEndDate,
            onComplete = viewModel::completeDatePicker,
            onDismissRequest = viewModel::setDatePickerVisible,
        )
    }
}

@Composable
private fun ArrestAppBar(
    allFilterSelected: Boolean,
    dangerFilterSelected: Boolean,
    heartRateFilterSelected: Boolean,
    onSelectFilter: (ArrestUiState.FilterType) -> Unit,
    onRefresh: () -> Unit,
    onOpenDatePicker: () -> Unit,
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
                text = stringResource(id = R.string.arrest_title),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = modifier.weight(1f))
            Icon(
                modifier = modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onRefresh)
                    .padding(5.dp),
                imageVector = Icons.Rounded.Refresh,
                tint = LogiBlue,
                contentDescription = null
            )
            Icon(
                modifier = modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onOpenDatePicker)
                    .padding(5.dp),
                imageVector = Icons.Rounded.DateRange,
                tint = LogiBlue,
                contentDescription = null
            )
        }
        ArrestFilter(
            allFilterSelected = allFilterSelected,
            dangerFilterSelected = dangerFilterSelected,
            heartRateFilterSelected = heartRateFilterSelected,
            onSelectFilter = onSelectFilter
        )
    }
}


@Preview(name = "ArrestScreen")
@Composable
private fun PreviewArrestScreen() {
    ArrestScreen(rememberNavController())
}
