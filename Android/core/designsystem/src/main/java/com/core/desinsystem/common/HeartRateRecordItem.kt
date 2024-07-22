package com.core.desinsystem.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeartRateRecordItem(
    bpm: Int,
    time: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HeartRateDesc(
                bpm = bpm,
                time = time,
                modifier = modifier.weight(1f)
            )
            HeartRateLevel(
                bpm = bpm
            )
        }
        HorizontalDivider()
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
            Text(
                text = "$bpm",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = modifier.padding(start = 4.dp),
                text = "bpm",
                style = MaterialTheme.typography.titleSmall
            )
        }
        Text(
            text = time,
            style = MaterialTheme.typography.labelLarge
        )
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
        Icon(
            modifier = modifier.size(15.dp),
            imageVector = Icons.Rounded.CheckCircle,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "정상",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(name = "HeartRateRecord")
@Composable
private fun PreviewHeartRateRecord() {
    HeartRateRecordItem(100, "2023.06.15")
}