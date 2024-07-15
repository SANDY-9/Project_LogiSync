package com.sandy.statistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.sandy.statistics.compoents.HeartRateChart
import com.sandy.statistics.compoents.HeartRateDescriptionCard
import com.sandy.statistics.compoents.HeartRateRecordItem
import com.sandy.statistics.compoents.MyDatePicker
import com.sandy.statistics.model.StatisticsUiState

@Composable
fun StatisticsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = hiltViewModel(),
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StatisticsAppBar({})
        LazyColumn(
            modifier = modifier.weight(1f),
        ) {
            item {
                StatisticsContent(state = state)
            }
            items(state.recordItem.size) { index ->
                HeartRateRecordItem(state.recordItem[index])
            }
        }
        MyDatePicker()
    }
}

@Composable
private fun StatisticsAppBar(
    onClink: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
    ) {

        Text(
            modifier = modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart),
            text = stringResource(id = R.string.heart_rate_statistics_title),
            style = MaterialTheme.typography.headlineSmall,
        )

        IconButton(
            modifier = modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterEnd),
            onClick = onClink,
        ) {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun StatisticsContent(
    state: StatisticsUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        HeartRateChart(
            year = state.year,
            month = state.month,
            day = state.day,
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
