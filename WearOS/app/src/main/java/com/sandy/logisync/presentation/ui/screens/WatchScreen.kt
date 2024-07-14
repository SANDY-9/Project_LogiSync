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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate
import com.sandy.logisync.presentation.ui.screens.component.HeartRateItem
import java.time.LocalDateTime

@Composable
fun WatchScreen(
    measuredHeartRate: MeasuredHeartRate,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "최근 심박수")
        HeartRateItem(measuredHeartRate = measuredHeartRate)
        Spacer(modifier = modifier.height(8.dp))
        Button(
            modifier = modifier
                .padding(4.dp)
                .size(70.dp, 40.dp),
            onClick = { /*TODO*/ },
        ) {
            Text(text = "수집")
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
                date = LocalDateTime.now(),
                value = 60
            )
        )
    )
}
