package com.sandy.statistics.compoents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.statistics.R

@Composable
fun HeartRateRecordItem(
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
            HeartRateDesc(modifier = modifier.weight(1f))
            HeartRateLevel()
        }
        HorizontalDivider()
    }
}

@Composable
private fun HeartRateDesc(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ){
            Text(
                text = "76",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = modifier.padding(start = 4.dp),
                text = stringResource(id = R.string.heart_rate_unit),
                style = MaterialTheme.typography.titleSmall
            )
        }
        Text(
            text = "오후 15:00",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
private fun HeartRateLevel(
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
    HeartRateRecordItem()
}
