package com.sandy.statistics.admin

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.EmptyRecordView
import com.core.desinsystem.common.MyDateRangePickerBottomSheet
import com.core.desinsystem.common.RecordItemHeartRate
import com.core.desinsystem.theme.LogiBlue
import com.core.model.Arrest
import com.core.model.User
import com.core.navigation.Args
import com.sandy.statistics.R
import com.sandy.statistics.admin.model.StatisticsAdminUiState
import com.sandy.statistics.compoents.HeartRateChart
import com.sandy.statistics.compoents.HeartRateDescriptionCard
import com.sandy.statistics.model.StatisticsUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatisticsAdminScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: StatisticsAdminViewModel = hiltViewModel()
) {

    LaunchedEffect(navController.previousBackStackEntry) {
        navController.previousBackStackEntry?.savedStateHandle?.get<User>(Args.USER)?.let {user ->
            viewModel.getHeartRateList(user)
        }
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        LazyColumn {
            stickyHeader {
                HeartRateStatisticsAdminAppBar(
                    user = state.user,
                    onReset = viewModel::resetChart,
                    onDatePickerClick = viewModel::setDatePickerVisible,
                    onNavigateUp = navController::navigateUp
                )
            }
            item {
                StatisticsContent(
                    state = state,
                    onPrevClick = viewModel::getPrevDateChart,
                    onNextClick = viewModel::getNextDateChart,
                    onItemClick = viewModel::selectItem,
                )
            }

            stickyHeader {
                HeartRateRecordAppBar()
            }

            items(items = state.selectRecordItem){ item ->
                RecordItemHeartRate(item.bpm, item.time())
            }
        }

        if(state.isSelectItemEmpty) {
            EmptyRecordView(modifier = modifier.weight(1f))
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
private fun HeartRateStatisticsAdminAppBar(
    user: User?,
    onReset: () -> Unit,
    onDatePickerClick: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(start = 5.dp, end = 13.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(
            onClick = onNavigateUp
        ) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
        }
        Text(
            text = user?.let { "${it.id} (${it.name})" } ?: "",
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = modifier.weight(1f))

        Icon(
            modifier = modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable(onClick = onReset)
                .padding(5.dp),
            imageVector = Icons.Rounded.Refresh,
            tint = LogiBlue,
            contentDescription = null
        )
        Icon(
            modifier = modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable(onClick = onDatePickerClick)
                .padding(5.dp),
            imageVector = Icons.Rounded.DateRange,
            tint = LogiBlue,
            contentDescription = null
        )
    }
}

@Composable
private fun StatisticsContent(
    state: StatisticsAdminUiState,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HeartRateChart(
            type = state.chartType,
            date = state.pickedDate,
            onPrevClick = onPrevClick,
            onNextClick = onNextClick,
            chartItem = state.chartItem,
            selectPosition = state.selectPosition,
            onItemClick = onItemClick,
            periodTitle = state.selectDateTitle
        )
        Spacer(modifier = modifier.height(24.dp))
        if(!state.isSelectItemEmpty) {
            HeartRateDescriptionCard(
                minBPM = state.minBPM,
                maxBPM = state.maxBPM,
            )
        }
    }
}

@Composable
private fun HeartRateRecordAppBar(
    minBpm: Int = 60,
    maxBpm: Int = 100,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp)
            .background(color = Color.White),
    ) {
        Text(
            text = stringResource(id = R.string.heart_rate_record_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "정상범위 : $minBpm - $maxBpm bpm",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
        )
    }
}

@Preview(name = "StatisticsAdminScreen")
@Composable
private fun PreviewStatisticsAdminScreen() {
    StatisticsAdminScreen(rememberNavController())
}
