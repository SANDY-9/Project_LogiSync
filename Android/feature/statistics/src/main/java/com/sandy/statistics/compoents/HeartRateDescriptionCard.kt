package com.sandy.statistics.compoents

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
fun HeartRateDescriptionCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeartRateRange()
            Spacer(modifier = modifier.height(8.dp))
            HeartRateDescription()
            HeartRateAvg()
        }
    }
}

@Composable
private fun HeartRateRange(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(15.dp),
            imageVector = Icons.Rounded.Favorite,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "최소값",
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(text = stringResource(id = R.string.heart_rate_avg_unit))
        Text(
            text = "최대값",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun HeartRateDescription(
    modifier: Modifier = Modifier,
) {
    Row {
        Text(
            text = stringResource(id = R.string.heart_rate_desc_title),
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(text = stringResource(id = R.string.heart_rate_desc_lower))
    }
}

@Composable
private fun HeartRateAvg(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = stringResource(id = R.string.heart_rate_desc_avg),
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.heart_rate_unit),
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview(name = "HeartRateDescription")
@Composable
private fun PreviewHeartRateDescription() {
    HeartRateDescriptionCard()
}
