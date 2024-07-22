package com.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.BasicOutlinedButton
import com.core.desinsystem.common.HeartRateLabel
import com.core.desinsystem.common.LinearHeartRateGraph
import com.core.desinsystem.common.LogiCard
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.DarkRed
import com.core.model.HeartRate
import com.feature.home.R

@Composable
internal fun HeartRateInfo(
    heartRate: HeartRate?,
    onRequestCollect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_heart_rate_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.size(12.dp))
        LogiCard{
            if (heartRate != null) {
                NotEmptyHeartRateView(
                    heartRate = heartRate,
                    onRequestCollect = onRequestCollect,
                )
            }
            else {
                EmptyMeasuredHeartRateView(
                    modifier = modifier.fillMaxWidth(),
                    onRequestCollect = onRequestCollect,
                )
            }
        }
    }
}

@Composable
private fun EmptyMeasuredHeartRateView(
    onRequestCollect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.home_heart_rate_empty),
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = modifier.width(8.dp))
        HeartRateCollectButton(
            onClick = onRequestCollect,
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
        title = stringResource(id = R.string.home_heart_rate_collect_title),
        onClick = onClick,
    )
}


@Preview(name = "HeartRateInfo")
@Composable
private fun PreviewHeartRateInfo() {
    HeartRateInfo(HeartRate(), {})
}
