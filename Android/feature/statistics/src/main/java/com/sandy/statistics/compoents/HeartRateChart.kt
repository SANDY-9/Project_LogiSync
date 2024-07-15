package com.sandy.statistics.compoents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeartRateChart(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ChartTitle()
        Chart()
    }
}

@Composable
private fun ChartTitle() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null
            )
        }
        Text(
            text = "2024년 7월 1일 월요일",
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

private val stroke = Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f, 10f), phase = 10f))
@Composable
private fun Chart(
    item: List<Pair<Int, Int>> = listOf(
        50 to 70,
        60 to 100,
        95 to 100,
        83 to 85,
        100 to 138,
        70 to 138,
        65 to 90,
        77 to 86,
        65 to 158,
        95 to 95,
        95 to 182,
        71 to 98,
    ),
    modifier: Modifier = Modifier,
) {
    Box(
        modifier.fillMaxWidth()
    ) {

        // 180 bpm
        HorizontalDivider(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp, end = 25.dp),
        )

        Text(
            modifier = modifier
                .width(30.dp)
                .align(Alignment.TopEnd)
                .padding(top = 12.dp),
            text = "180",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
        )

        // 30 bpm
        HorizontalDivider(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp, end = 25.dp)
        )

        Text(
            modifier = modifier
                .width(30.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 43.dp),
            text = "30",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
        )

        // 정상범위
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(200.dp)
                .padding(
                    top = 120.dp,
                    bottom = 60.dp,
                    end = 25.dp
                )
                .background(Color.Yellow)

        )

        Text(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(top = 112.dp),
            text = "정상\n범위",
            style = MaterialTheme.typography.labelSmall,
        )

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(end = 30.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(
                items = item,
            ) { index, item ->
                ChartItem(
                    index + 8,
                    item.first,
                    item.second,
                )
            }
        }
    }
}

@Composable
private fun ChartItem(
    time: Int,
    minBps: Int,
    maxBps: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(30.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = modifier
                .height(200.dp)
                .width(12.dp)
                .padding(
                    top = (200 - maxBps).dp,
                    bottom = (minBps).dp
                )
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(100.dp)
                )
        )
        Box(
            modifier = modifier.height(20.dp),
        ) {
            Text(
                modifier = modifier.align(Alignment.TopCenter),
                text = "$time",
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Preview(name = "HeartRateChart")
@Composable
private fun PreviewHeartRateChart() {
    HeartRateChart()
}
