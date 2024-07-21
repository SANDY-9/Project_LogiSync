package com.sandy.statistics.compoents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.DottedLine
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.LightGreen
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
    selectPosition: Int?,
    onItemClick: (Int) -> Unit,
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
        Chart(
            item = chartItem,
            selectPosition = selectPosition,
            onItemClick = onItemClick,
        )
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

private val stroke = Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
@Composable
private fun Chart(
    item: List<HeartRateChartItem>,
    selectPosition: Int?,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier.fillMaxWidth()
    ) {

        // 180 bpm
        DottedLine(
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
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )

        // 30 bpm
        DottedLine(
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
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )

        // 정상범위
        Spacer (
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(200.dp)
                .padding(
                    top = 100.dp,
                    bottom = 60.dp,
                    end = 25.dp
                )
                .drawBehind {
                    drawRoundRect(
                        color = DarkGreen,
                        style = stroke,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
        )

        Text(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(top = 103.dp),
            text = "정상\n범위",
            color = LightGreen,
            style = MaterialTheme.typography.labelSmall,
        )

        HeartRateGraph(
            chartItem = item,
            selectPosition = selectPosition,
            onItemClick = onItemClick,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(end = 30.dp),
        )
    }
}
