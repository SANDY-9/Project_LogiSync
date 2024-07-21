package com.sandy.statistics.compoents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.HeartRateRangeLabel
import com.core.desinsystem.common.LinearHeartRateRangeGraph
import com.core.desinsystem.common.LogiCard
import com.sandy.statistics.R

@Composable
fun HeartRateDescriptionCard(
    minBPM: Int?,
    maxBPM: Int?,
    modifier: Modifier = Modifier
) {
    if(minBPM == null || maxBPM == null) return
    LogiCard{
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeartRateRangeLabel(minBPM, maxBPM)
            Spacer(modifier = modifier.height(12.dp))
            LinearHeartRateRangeGraph(minBPM, maxBPM)
            Spacer(modifier = modifier.height(8.dp))
            HeartRateAvg()
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
            text = stringResource(id = R.string.heart_rate_desc_avg),
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray,
        )
    }
}

@Preview(name = "HeartRateDescription")
@Composable
private fun PreviewHeartRateDescription() {
    HeartRateDescriptionCard(100, 50)
}
