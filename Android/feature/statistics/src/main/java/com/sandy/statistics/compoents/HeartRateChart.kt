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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.core.utils.DateUtil
import com.sandy.statistics.model.HeartRateChartItem
import com.sandy.statistics.utils.minDate
import java.time.LocalDate

@Composable
fun HeartRateChart(
    date: LocalDate,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    chartItem: List<HeartRateChartItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ChartTitle(
            date = date,
            onPrevClick = onPrevClick,
            onNextClick = onNextClick,
        )
        Chart(chartItem)
    }
}

private val today = LocalDate.now()

@Composable
private fun ChartTitle(
    date: LocalDate,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPrevClick,
            enabled = !date.isEqual(minDate)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null
            )
        }
        Text(
            text = DateUtil.convertFullDate(date),
        )
        IconButton(
            onClick = onNextClick,
            enabled = !date.isEqual(today)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

private val stroke = Stroke(
    width = 2f,
    pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f, 10f), phase = 10f)
)

@Composable
private fun Chart(
    item: List<HeartRateChartItem>,
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
            items(items = item) { item ->
                ChartItem(
                    time = item.hour,
                    minBpm = item.minBpm,
                    maxBpm = item.maxBpm,
                )
            }
        }
    }
}

@Composable
private fun ChartItem(
    time: Int,
    maxBpm: Int?,
    minBpm: Int?,
    modifier: Modifier = Modifier,
) {
    val graphPadding = PaddingValues(
        top = (maxBpm?.let { 200 - it } ?: 200).dp,
        bottom = (minBpm ?: 200).dp
    )
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
                .padding(graphPadding)
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
