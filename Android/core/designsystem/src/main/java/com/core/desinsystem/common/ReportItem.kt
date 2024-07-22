package com.core.desinsystem.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.icons.PulseAlert
import com.core.desinsystem.theme.DarkRed

@Composable
fun ReportItemNormal(
    desc: String,
    date: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LogiCardClickable(
        onClick = onItemClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Rounded.Warning,
                contentDescription = null,
                tint = DarkRed,
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall.copy(
                    letterSpacing = (-0.5).sp
                )
            )
        }
    }
}

@Composable
fun ReportItemHeartRate(
    bpm: Int?,
    desc: String,
    date: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LogiCardClickable(
        onClick = onItemClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.PulseAlert,
                contentDescription = null,
                tint = DarkRed,
            )
            Spacer(modifier = modifier.width(6.dp))
            Row {
                Text(
                    text = "$desc ",
                    style = MaterialTheme.typography.labelLarge.copy(
                        letterSpacing = (-0.2).sp
                    )
                )
                Text(
                    modifier = modifier.padding(top = 1.dp),
                    text = "($bpm)",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp
                    ),
                    color = DarkRed
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall.copy(
                    letterSpacing = (-0.5).sp
                ),
            )
        }
    }
}
