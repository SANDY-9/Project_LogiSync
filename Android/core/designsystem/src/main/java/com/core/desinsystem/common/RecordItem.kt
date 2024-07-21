package com.core.desinsystem.common

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
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.DarkRed
import com.core.desinsystem.theme.LightGreen
import com.core.desinsystem.theme.LogiLightGray
import com.core.desinsystem.theme.LogiOrange

@Composable
fun RecordItemHeartRate(
    bpm: Int,
    date: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier.weight(1f),
            ) {
                HeartRateLabelSmall(bpm = bpm)
                Spacer(modifier = modifier.height(2.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp
                    ),
                    color = Color.Gray,
                )
            }
            HeartRateLevel(
                bpm = bpm
            )
        }
        HorizontalDivider(color = LogiLightGray)
    }
}

@Composable
private fun HeartRateLevel(
    bpm: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(bpm in 60..100) {
            Icon(
                modifier = modifier.size(15.dp),
                imageVector = Icons.Rounded.CheckCircle,
                tint = DarkGreen,
                contentDescription = null
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = "정상",
                style = MaterialTheme.typography.bodyMedium,
                color = DarkGreen,
            )
        }
        else {
            Icon(
                modifier = modifier.size(15.dp),
                imageVector = Icons.Rounded.Warning,
                tint = LogiOrange,
                contentDescription = null
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = "주의",
                style = MaterialTheme.typography.bodyMedium,
                color = LogiOrange,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    RecordItemHeartRate(bpm = 120, date = "20204년 07월 31일 오후 17:55")
}
