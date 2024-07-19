package com.sandy.logisync.presentation.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.scrollAway
import androidx.wear.tooling.preview.devices.WearDevices
import com.sandy.logisync.R
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate
import com.sandy.logisync.presentation.ui.screens.component.HeartRateCard
import java.time.LocalDateTime

@Composable
fun WatchScreen(
    measuredHeartRate: MeasuredHeartRate,
    onCollect: () -> Unit,
    onArrest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        timeText = {
            TimeText(modifier = modifier.scrollAway(listState))
        },
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState,
            )
        }
    ) {
        ScalingLazyColumn(
            modifier = modifier.fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Column {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.caption2,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                }
            }
            item {
                Column {
                    HeartRateCard(heartRate = measuredHeartRate.heartRate!!)
                    Spacer(modifier = modifier.height(8.dp))
                }
            }
            item {
                Button(
                    modifier = modifier
                        .padding(4.dp)
                        .size(70.dp, 40.dp),
                    onClick = onCollect,
                ) {
                    Text(text = stringResource(id = R.string.heart_rate_collect))
                }
            }
            item {
                Button(
                    modifier = modifier
                        .padding(4.dp)
                        .size(70.dp, 40.dp),
                    onClick = onArrest,
                ) {
                    Text(text = stringResource(id = R.string.heart_rate_arrest))
                }
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun WatchScreenPreview() {
    WatchScreen(
        MeasuredHeartRate(
            MeasuredAvailability.AVAILABLE,
            HeartRate(
                time = LocalDateTime.now(),
                bpm = 60
            )
        ),
        {},
        {}
    )
}
