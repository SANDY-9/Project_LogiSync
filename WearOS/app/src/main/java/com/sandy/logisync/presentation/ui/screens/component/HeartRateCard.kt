package com.sandy.logisync.presentation.ui.screens.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.tooling.preview.devices.WearDevices
import com.sandy.logisync.R
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.presentation.ui.theme.BackgroundDeem
import com.sandy.logisync.presentation.ui.theme.DeepRed
import java.time.LocalDateTime

@Composable
fun HeartRateCard(
    heartRate: HeartRate?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BackgroundDeem,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.Top,
    ) {
        Icon(
            modifier = modifier.padding(top = 6.dp),
            imageVector = Icons.Rounded.Favorite,
            tint = DeepRed,
            contentDescription = null,
        )
        if(heartRate != null) {
            NotEmptyHeartRate(
                heartRate = heartRate,
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
            )
        }
        else {
            EmptyHeartRate()
        }
    }
}

@Composable
private fun NotEmptyHeartRate(
    heartRate: HeartRate,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.heart_rate_title),
                style = MaterialTheme.typography.caption2,
                color = Color.White,
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = "${heartRate.bpm}",
                style = MaterialTheme.typography.body1,
                color = Color.White,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.padding(bottom = 3.dp),
                text = stringResource(id = R.string.heart_rate_unit),
                style = MaterialTheme.typography.caption3.copy(
                    fontSize = 8.sp,
                ),
                color = Color.White,
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = heartRate.dateStr(),
            style = MaterialTheme.typography.caption3,
            color = Color.White,
        )
        Text(
            text = heartRate.timeStr(),
            style = MaterialTheme.typography.caption3.copy(
                fontSize = 8.sp,
            ),
            color = Color.White,
        )
    }
}

@Composable
private fun EmptyHeartRate(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.fillMaxWidth().padding(start = 8.dp),
        text = stringResource(id = R.string.heart_rate_empty),
        style = MaterialTheme.typography.caption3,
        color = Color.White,
    )
}

@Preview(device = WearDevices.SMALL_ROUND, uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewHeartRateItem() {
    HeartRateCard(HeartRate(70, LocalDateTime.now()))
}
