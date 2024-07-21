package com.core.desinsystem.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.theme.HeartRed

@Composable
fun HeartRateLabel(
    bpm: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(15.dp),
            imageVector = Icons.Rounded.Favorite,
            tint = HeartRed,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = bpm,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(500)
            )
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            modifier = modifier.padding(top = 6.dp),
            text = "bpm",
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(500)
            )
        )
    }
}

@Composable
fun HeartRateRangeLabel(
    minBpm: Int,
    maxBpm: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(15.dp),
            imageVector = Icons.Rounded.Favorite,
            tint = HeartRed,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "$minBpm - $maxBpm",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(500)
            )
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            modifier = modifier.padding(top = 6.dp),
            text = "bpm",
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(500)
            )
        )
    }
}

@Composable
fun HeartRateLabelSmall(
    bpm: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(10.dp),
            imageVector = Icons.Rounded.Favorite,
            tint = HeartRed,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(6.dp))
        Text(
            text = "$bpm",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400)
            )
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            modifier = modifier.padding(top = 6.dp),
            text = "bpm",
            style = TextStyle(
                fontSize = 8.sp,
                fontWeight = FontWeight(500)
            )
        )
    }
}

@Composable
@Preview
private fun Preview() {
    HeartRateLabel("40")
}
