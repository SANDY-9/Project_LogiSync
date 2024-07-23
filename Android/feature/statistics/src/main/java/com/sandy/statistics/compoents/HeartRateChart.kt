package com.sandy.statistics.compoents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.core.desinsystem.theme.HeartRed
import com.core.desinsystem.theme.LightGreen
import com.core.desinsystem.theme.LogiExtraLightGray
import com.core.desinsystem.theme.LogiOrange
import com.core.desinsystem.theme.LogiOrangeDeem
import com.core.desinsystem.theme.LogiSemiGray
import com.sandy.statistics.model.HeartRateChartItem
import com.sandy.statistics.model.StatisticsUiState
import java.time.LocalDate

@Composable
fun HeartRateChart(
    type: StatisticsUiState.ChartType,
    date: LocalDate,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    chartItem: List<HeartRateChartItem>,
    selectPosition: Int?,
    onItemClick: (Int) -> Unit,
    periodTitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if(type == StatisticsUiState.ChartType.DAILY) {
            DailyChartTitle(
                date = date,
                onPrevClick = onPrevClick,
                onNextClick = onNextClick,
            )
        }
        else {
            PeriodChartTitle(
                periodTitle = periodTitle,
            )
        }
        Spacer(modifier = modifier.height(12.dp))
        Chart(
            type = type,
            item = chartItem,
            selectPosition = selectPosition,
            onItemClick = onItemClick,
        )
    }
}

internal val stroke = Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
@Composable
private fun Chart(
    type: StatisticsUiState.ChartType,
    item: List<HeartRateChartItem>,
    selectPosition: Int?,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxWidth()
            .border(
                color = LogiSemiGray,
                shape = RoundedCornerShape(16.dp),
                width = 1.dp
            )
            .padding(horizontal = 16.dp, vertical = 4.dp)
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
            color = DarkGreen,
            style = MaterialTheme.typography.labelSmall,
        )

        HeartRateGraph(
            type = type,
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
