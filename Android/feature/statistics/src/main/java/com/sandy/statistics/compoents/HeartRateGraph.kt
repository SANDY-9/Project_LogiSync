package com.sandy.statistics.compoents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.common.HeartRateBpmChartItem
import com.core.desinsystem.common.noRippleClickable
import com.core.desinsystem.theme.LogiBlue
import com.core.desinsystem.theme.LogiLightGray
import com.core.desinsystem.theme.TransparentWhiteDeem
import com.sandy.statistics.model.HeartRateChartItem
import com.sandy.statistics.model.StatisticsUiState

@Composable
fun HeartRateGraph(
    type: StatisticsUiState.ChartType,
    chartItem: List<HeartRateChartItem>,
    selectPosition: Int?,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    LaunchedEffect(chartItem) {
        lazyListState.animateScrollToItem(selectPosition ?: 0)
    }
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyListState
    ) {
        itemsIndexed(items = chartItem) { position, item ->
            ChartItem(
                type = type,
                date = item.date,
                minBpm = item.minBpm,
                maxBpm = item.maxBpm,
                clickItem = position == selectPosition,
                onItemClick = { onItemClick(position) }
            )
        }
    }
}

private val selectModifier = Modifier
    .border(
        width = 1.dp,
        color = LogiLightGray,
        shape = RoundedCornerShape(50.dp)
    )
    .background(
        color = TransparentWhiteDeem,
        shape = RoundedCornerShape(50.dp)
    )

@Composable
private fun ChartItem(
    type: StatisticsUiState.ChartType,
    date: String,
    maxBpm: Int?,
    minBpm: Int?,
    clickItem: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(40.dp)
            .noRippleClickable(onClick = onItemClick)
            .then(
                if (clickItem) selectModifier else modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeartRateBpmChartItem(maxBpm = maxBpm, minBpm = minBpm)
        Box(
            modifier = modifier.height(20.dp)
        ) {
            Text(
                modifier = modifier.align(Alignment.TopCenter),
                text = date,
                style = if(clickItem) selectTextStyle else unSelectTextStyle,
            )
        }
    }
}

private val selectTextStyle = TextStyle(
    color = LogiBlue,
    fontSize = 12.sp,
    letterSpacing = (-0.5).sp,
    fontWeight = FontWeight.Bold
)

private val unSelectTextStyle = TextStyle(
    fontSize = 12.sp,
    letterSpacing = (-0.5).sp,
    fontWeight = FontWeight.Normal
)
