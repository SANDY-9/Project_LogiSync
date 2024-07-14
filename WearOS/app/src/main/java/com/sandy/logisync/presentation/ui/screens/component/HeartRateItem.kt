package com.sandy.logisync.presentation.ui.screens.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.sandy.logisync.R
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate

@Composable
fun HeartRateItem(
    measuredHeartRate: MeasuredHeartRate,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (measuredHeartRate.availability) {
            MeasuredAvailability.ACQUIRING -> HeartRateLoading()
            MeasuredAvailability.AVAILABLE -> AvailableHeartRate(
                heartRate = measuredHeartRate.heartRate
            )
            MeasuredAvailability.UNAVAILABLE_DEVICE_OFF_BODY -> DeviceOffBodyMessage()
            MeasuredAvailability.UNAVAILABLE -> UnavailableHeartRateMessage()
            MeasuredAvailability.NONE -> Unit
        }

    }
}

@Composable
private fun AvailableHeartRate(
    heartRate: HeartRate?,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Rounded.Favorite,
            tint = Color.Red,
            contentDescription = null
        )
        Spacer(modifier = modifier.size(8.dp))
        Text(
            style = MaterialTheme.typography.display2,
            text = "${heartRate?.bpm}"
        )
    }
    Text(
        text = heartRate?.time.toString(),
        style = MaterialTheme.typography.caption3
    )
}

@Composable
private fun DeviceOffBodyMessage() {
    Text(
        text = stringResource(id = R.string.heart_rate_error_body_off),
    )
}

@Composable
private fun UnavailableHeartRateMessage() {
    Text(
        text = stringResource(id = R.string.heart_rate_error_body_disabled),
        textAlign = TextAlign.Center,
    )
}

@Preview(device = WearDevices.SMALL_ROUND, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewHeartRate() {
    HeartRateItem(
        MeasuredHeartRate(MeasuredAvailability.UNAVAILABLE, null)
    )
}
