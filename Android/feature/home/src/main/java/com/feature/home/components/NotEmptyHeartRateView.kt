package com.feature.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.BasicOutlinedButton
import com.core.desinsystem.common.DottedLine
import com.core.desinsystem.common.HeartRateLabelLarge
import com.core.desinsystem.common.LinearHeartRateGraph
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.DarkRed
import com.core.model.HeartRate
import com.feature.home.R

@Composable
internal fun NotEmptyHeartRateView(
    checkWearableLogin: Boolean,
    heartRate: HeartRate,
    onRequestCollect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box{
        Column(
            modifier = modifier.padding(end = 24.dp)
        ) {
            HeartRateRecord(
                modifier = modifier.padding(top = 4.dp),
                heartRate = heartRate,
            )
            DottedLine(modifier = modifier.padding(top = 8.dp, bottom = 16.dp))
            HeartRateAnalysis(avgRange = heartRate.avgRange)
            LinearHeartRateGraph(bpm = heartRate.bpm,)
        }
        if(checkWearableLogin) {
            HeartRateCollectButton(
                modifier = modifier.align(Alignment.TopEnd),
                onClick = onRequestCollect,
            )
        }
    }
}

@Composable
private fun HeartRateRecord(
    heartRate: HeartRate,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        HeartRateLabelLarge(bpm = "${heartRate.bpm}")
        Text(
            text = heartRate.time(),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun HeartRateAnalysis(
    avgRange: HeartRate.AvgRange,
) {
    when(avgRange) {
        HeartRate.AvgRange.NORMAL -> NormalAvg()
        HeartRate.AvgRange.LOW -> LowAvg()
        HeartRate.AvgRange.HIGH -> HighAvg()
    }
}

@Composable
private fun NormalAvg(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.home_heart_rate_avg_normal1),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            modifier = modifier.padding(horizontal = 3.dp),
            text = stringResource(id = R.string.home_heart_rate_avg_normal2),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = DarkGreen,
                fontWeight = FontWeight.Bold,
            ),
        )
        Text(
            text = stringResource(id = R.string.home_heart_rate_avg_normal3),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun HighAvg(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.home_heart_rate_avg),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            modifier = modifier.padding(horizontal = 3.dp),
            text = stringResource(id = R.string.home_heart_rate_avg_high),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = DarkRed,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@Composable
private fun LowAvg(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.home_heart_rate_avg),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            modifier = modifier.padding(horizontal = 3.dp),
            text = stringResource(id = R.string.home_heart_rate_avg_low),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = DarkRed,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@Composable
private fun HeartRateCollectButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicOutlinedButton(
        modifier = modifier,
        title = stringResource(id = R.string.home_heart_rate_collect),
        onClick = onClick,
    )
}
