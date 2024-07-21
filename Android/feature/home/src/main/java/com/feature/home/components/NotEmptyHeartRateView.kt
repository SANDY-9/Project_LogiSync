package com.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.core.desinsystem.common.HeartRateLabel
import com.core.desinsystem.common.LinearHeartRateGraph
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.DarkRed
import com.core.model.HeartRate
import com.feature.home.R

@Composable
internal fun NotEmptyHeartRateView(
    heartRate: HeartRate,
    onRequestCollect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row{
        Column(
            modifier = modifier
                .weight(1f)
                .padding(end = 24.dp)
        ) {
            HeartRateRecord(
                heartRate = heartRate,
            )
            Spacer(modifier = modifier.height(4.dp))
            HeartRateAnalysis(
                avgRange = heartRate.avgRange,
            )
            Spacer(modifier = modifier.height(4.dp))
            LinearHeartRateGraph(bpm = heartRate.bpm)
        }
        HeartRateCollectButton(
            onClick = onRequestCollect,
        )
    }
}

@Composable
private fun HeartRateRecord(
    heartRate: HeartRate,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HeartRateLabel(bpm = "${heartRate.bpm}")
        Text(
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(start = 8.dp, bottom = 3.dp),
            text = heartRate.time(),
            style = MaterialTheme.typography.labelSmall,
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
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            modifier = modifier.padding(horizontal = 3.dp),
            text = stringResource(id = R.string.home_heart_rate_avg_normal2),
            style = MaterialTheme.typography.labelMedium.copy(
                color = DarkGreen,
                fontWeight = FontWeight.Bold,
            ),
        )
        Text(
            text = stringResource(id = R.string.home_heart_rate_avg_normal3),
            style = MaterialTheme.typography.labelMedium,
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
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            modifier = modifier.padding(horizontal = 3.dp),
            text = stringResource(id = R.string.home_heart_rate_avg_high),
            style = MaterialTheme.typography.labelMedium.copy(
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
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            modifier = modifier.padding(horizontal = 3.dp),
            text = stringResource(id = R.string.home_heart_rate_avg_low),
            style = MaterialTheme.typography.labelMedium.copy(
                color = DarkRed,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@Composable
private fun HeartRateCollectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    BasicOutlinedButton(
        modifier = modifier,
        title = stringResource(id = R.string.home_heart_rate_collect),
        onClick = onClick,
    )
}
