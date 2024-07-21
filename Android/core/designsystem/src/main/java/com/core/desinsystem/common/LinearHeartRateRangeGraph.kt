package com.core.desinsystem.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.LightGreen
import com.core.desinsystem.theme.LogiBlue

@Composable
fun LinearHeartRateRangeGraph(
    minBpm: Int,
    maxBpm: Int,
    modifier: Modifier = Modifier,
) {
    val minOff = remember(minBpm) {
        when {
            minBpm < 30 -> 8
            minBpm > 150 -> 130
            else -> minBpm - 20
        }
    }
    val minX = remember(minOff) { minOff * 5f }

    val maxOff = remember(maxBpm) {
        when {
            maxBpm < 30 -> 8
            maxBpm > 150 -> 130
            else -> maxBpm - 20
        }
    }
    val maxX = remember(maxOff) { maxOff * 5f }
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier.size(
        width = 255.dp,
        height = 35.dp
    )){

        drawRoundRect(
            color = Color.LightGray,
            topLeft = Offset(0f, 0f),
            size = Size(700f, 40f),
            cornerRadius = CornerRadius(50f)
        )

        // 정상범위 : 60-100
        drawRect(
            color = LightGreen,
            topLeft = Offset(200f, 0f),
            size = Size(200f, 40f),
        )

        // 두 점을 잇는 선 그리기
        drawLine(
            color = LogiBlue,
            start = Offset(minX, 20f),
            end = Offset(maxX, 20f),
            strokeWidth = 40f
        )

        if(maxBpm - minBpm < 1 || minBpm < 30 && maxBpm < 30 || minBpm > 150 && maxBpm > 150) {
            drawCircle(
                color = LogiBlue,
                radius = 10f,
                center = Offset(minX, 20f)
            )
            drawLine(
                color = LogiBlue,
                start = Offset(minX - 2, 20f),
                end = Offset(maxX + 2, 20f),
                strokeWidth = 40f
            )
        }

        drawLine(
            color = DarkGreen,
            start = Offset(200f, 0f),
            end = Offset(200f, 90f),
            strokeWidth = 3f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 10f)
        )

        drawLine(
            color = DarkGreen,
            start = Offset(400f, 0f),
            end = Offset(400f, 90f),
            strokeWidth = 3f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 10f)
        )


        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(240f, 45f),
            text = "정상범위",
            style = TextStyle(
                color = DarkGreen,
                fontSize = 32.toSp()
            )
        )

        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(60f, 45f),
            text = "낮음",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 32.toSp()
            )
        )

        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(570f, 45f),
            text = "높음",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 32.toSp()
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    LinearHeartRateRangeGraph(30, 31)
}
