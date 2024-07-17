package com.sandy.statistics

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sandy.statistics.compoents.HeartRateChart
import com.sandy.statistics.compoents.HeartRateDescriptionCard
import com.sandy.statistics.compoents.HeartRateRecordItem
import com.sandy.statistics.compoents.MyDateRangePickerBottomSheet
import com.sandy.statistics.model.StatisticsUiState

@Composable
fun StatisticsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = hiltViewModel(),
) {

    val context = LocalContext.current as? Activity
    BackHandler(enabled = true) {
        context?.finish()
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StatisticsAppBar(
            onReset = viewModel::resetChart,
            onDatePickerClick = viewModel::setDatePickerVisible,
        )
        LazyColumn(
            modifier = modifier.weight(1f),
        ) {
            item {
                StatisticsContent(
                    state = state,
                    onPrevClick = viewModel::getPrevDateChart,
                    onNextClick = viewModel::getNextDateChart,
                    modifier = Modifier,
                )
            }
            items(state.recordItem.size) { index ->
                HeartRateRecordItem(state.recordItem[index])
            }
        }
        if (state.datePickerVisible) {
            MyDateRangePickerBottomSheet(
                selectedStartDateStr = state.selectedStartDateStr,
                selectedEndDateStr = state.selectedEndDateStr,
                onSelectedStartDate = viewModel::selectedStartDate,
                onSelectedEndDate = viewModel::selectedEndDate,
                onComplete = viewModel::requestHeartRates,
                onDismissRequest = viewModel::setDatePickerVisible,
            )
        }
    }
}

@Composable
private fun StatisticsAppBar(
    onReset: () -> Unit,
    onDatePickerClick: () -> Unit,
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
            text = stringResource(id = R.string.heart_rate_statistics_title),
            style = MaterialTheme.typography.headlineSmall,
        )

        Spacer(modifier = modifier.weight(1f))

        Icon(
            modifier = modifier
                .padding(end = 10.dp)
                .clickable(
                    onClick = onReset
                ),
            imageVector = Icons.Rounded.Refresh,
            contentDescription = null
        )

        Icon(
            modifier = modifier
                .padding(end = 16.dp)
                .clickable(
                    onClick = onDatePickerClick
                ),
            imageVector = Icons.Rounded.DateRange,
            contentDescription = null
        )
    }
}

@Composable
private fun StatisticsContent(
    state: StatisticsUiState,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        HeartRateChart(
            date = state.pickedDate,
            onPrevClick = onPrevClick,
            onNextClick = onNextClick,
            chartItem = state.chartItem,
        )
        Spacer(modifier = modifier.height(30.dp))
        HeartRateDescriptionCard(
            minBPM = state.minBPM,
            maxBPM = state.maxBPM,
        )
        Spacer(modifier = modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.heart_rate_record_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.size(12.dp))
    }
}

@Preview(name = "StatisticsScreen")
@Composable
private fun PreviewStatisticsScreen() {
    StatisticsScreen(rememberNavController())
}