package com.core.desinsystem.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.PulseAlert
import com.core.desinsystem.theme.DarkRed

@Composable
fun ReportItemNormal(
    modifier: Modifier = Modifier
) {
    LogiCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Rounded.Warning,
                contentDescription = null,
                tint = DarkRed,
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = "위급상황 신고",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = "2024.07.20 10:00",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
@Composable
fun ReportItemHeartRate(
    bpm: Int = 60,
    reportType: String = "초과",
    modifier: Modifier = Modifier,
) {
    LogiCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.PulseAlert,
                contentDescription = null,
                tint = DarkRed,
            )
            Spacer(modifier = modifier.width(4.dp))
            Row {
                Text(
                    text = "심박수 임계치 $reportType ",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "($bpm)",
                    style = MaterialTheme.typography.labelLarge,
                    color = DarkRed
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = "2024.07.20 10:00",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}


@Preview(name = "ReportItem")
@Composable
private fun PreviewReportItem() {
    ReportItemHeartRate()
}
