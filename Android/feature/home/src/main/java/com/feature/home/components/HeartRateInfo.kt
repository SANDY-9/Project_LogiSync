package com.feature.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.BasicOutlinedButton
import com.feature.home.R

@Composable
fun HeartRateInfo(
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_heart_rate_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.size(12.dp))
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
                modifier = modifier.padding(
                    start = 24.dp, end = 24.dp, top = 16.dp, bottom = 24.dp
                )
            ) {
                Column(
                    modifier = modifier.weight(1f).padding(end = 24.dp)
                ) {
                    HeartRateRecord()
                    HeartRateAnalysis()
                    Spacer(modifier = modifier.height(16.dp))
                    HeartRateGraph()
                }
                HeartRateCollectButton()
            }
        }
    }
}

@Composable
private fun HeartRateRecord(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = Icons.Rounded.Favorite,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "76",
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(bottom = 2.dp),
            text = "bpm",
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(start = 8.dp, bottom = 4.dp),
            text = "오후 14:00",
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
private fun HeartRateAnalysis(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.home_heart_rate_avg_normal),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
private fun HeartRateGraph(
    modifier: Modifier = Modifier,
) {
    LinearProgressIndicator(
        modifier = modifier.fillMaxWidth(),
        progress = { 1f }
    )
}

@Composable
private fun HeartRateCollectButton(
    modifier: Modifier = Modifier,
) {
    BasicOutlinedButton(
        modifier = modifier,
        title = stringResource(id = R.string.home_heart_rate_collect),
        onClick = { /*TODO*/ }
    )
}

@Preview(name = "HeartRateInfo")
@Composable
private fun PreviewHeartRateInfo() {
    HeartRateInfo()
}
