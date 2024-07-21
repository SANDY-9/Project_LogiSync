package com.core.desinsystem.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.LogiBlue

@Composable
fun HeartRateBpmChartItem(
    maxBpm: Int,
    minBpm: Int,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    Canvas(
        modifier = modifier.size(
            width = 30.dp,
            height = 200.dp
        )
    ) {
        with(density) {
            val canvasWidth = size.width
            val maxPosition = (200-maxBpm).dp.toPx()
            val minPosition = (200-minBpm).dp.toPx()

            // maxBpm 위치에 점 그리기
            drawCircle(
                color = LogiBlue,
                center = Offset(canvasWidth / 2, maxPosition),
                radius = 12f,
            )

            // minBpm 위치에 점 그리기
            drawCircle(
                color = LogiBlue,
                center = Offset(canvasWidth / 2, minPosition),
                radius = 12f,
            )

            // 두 점을 잇는 선 그리기
            drawLine(
                color = LogiBlue,
                start = Offset(canvasWidth / 2, maxPosition),
                end = Offset(canvasWidth / 2, minPosition),
                strokeWidth = 24f
            )
        }

    }
}

@Composable
@Preview
private fun Preview() {
    HeartRateBpmChartItem(50, 50)
}
