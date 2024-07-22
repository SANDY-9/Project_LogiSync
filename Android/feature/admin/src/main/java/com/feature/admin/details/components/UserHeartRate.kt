package com.feature.admin.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.HeartRateLabelLarge
import com.core.desinsystem.common.LinearHeartRateRangeGraph
import com.core.desinsystem.common.LogiCard
import com.core.desinsystem.icons.Check
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.DarkRed
import com.feature.admin.R
import java.time.LocalDateTime

@Composable
internal fun UserHeartRate(
    bpm: Int?,
    measureDateTime: LocalDateTime? = LocalDateTime.now(),
    isWarning: Boolean,
    modifier: Modifier = Modifier
) {
    LogiCard(
        modifier = modifier.padding(horizontal = 16.dp),
    ){
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(bpm != null) {
                HeartRate(
                    bpm = bpm ?: return@Column,
                    measureDateTime = measureDateTime ?: return@Column,
                    isWarning = isWarning,
                )
                Spacer(modifier = modifier.height(16.dp))
                LinearHeartRateRangeGraph(bpm, bpm)
                Spacer(modifier = modifier.height(8.dp))
                HeartRateAvg()
            }
            else {
                EmptyHeartRate()
            }
        }
    }
    Spacer(modifier = modifier.height(30.dp))
}

@Composable
private fun HeartRate(
    bpm: Int,
    measureDateTime: LocalDateTime,
    isWarning: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = modifier.weight(1f)
        ) {
            HeartRateLabelLarge("$bpm")
            Spacer(modifier = modifier.height(2.dp))
            Text(
                text = measureDateTime.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(isWarning) {
                Icon(
                    modifier = modifier.size(45.dp),
                    imageVector = Icons.Rounded.Warning,
                    tint = DarkRed,
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = R.string.details_state_warning),
                    color = DarkRed,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            else {
                Icon(
                    modifier = modifier.size(45.dp),
                    imageVector = Icons.Check,
                    tint = DarkGreen,
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = R.string.details_state_normal),
                    color = DarkGreen,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}


@Composable
private fun HeartRateAvg(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(15.dp),
            imageVector = Icons.Rounded.CheckCircle,
            tint = Color.Gray,
            contentDescription = null,
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.details_heart_rate_desc_avg),
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray,
        )
    }
}

@Composable
private fun EmptyHeartRate(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = modifier.size(20.dp),
            imageVector = Icons.Rounded.Info,
            tint = Color.Gray,
            contentDescription = null,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.details_heart_rate_null),
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
        )
    }
}
