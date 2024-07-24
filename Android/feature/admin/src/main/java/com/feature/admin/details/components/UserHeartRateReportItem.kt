package com.feature.admin.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.core.desinsystem.common.HeartRateLabel
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.LogiLightGray
import com.core.desinsystem.theme.LogiOrange
import java.time.LocalDateTime

@Composable
internal fun UserHeartRateReportItem(
    bpm: Int,
    time: String,
    isCritical: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LogiLightGray),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 24.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HeartRateDesc(
                    bpm = bpm,
                    time = time,
                    modifier = modifier.weight(1f)
                )
                HeartRateLevel(
                    isCritical = isCritical,
                    bpm = bpm,
                )
            }
        }
    }
}

@Composable
private fun HeartRateDesc(
    bpm: Int,
    time: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ){
            HeartRateLabel(bpm = "$bpm")

        }
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall.copy(
                letterSpacing = (-0.2).sp,
            ),
            color = Color.DarkGray,
        )
    }
}

@Composable
private fun HeartRateLevel(
    bpm: Int,
    isCritical: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(isCritical) {
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
        else {
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
    }
}

@Preview(name = "UserHeartRateReportItem")
@Composable
private fun PreviewUserHeartRateReportItem() {
    UserHeartRateReportItem(70, LocalDateTime.now().toString(), true)
}
