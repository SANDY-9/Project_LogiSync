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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.DarkGreen
import com.core.desinsystem.theme.LightGreen
import com.core.desinsystem.theme.LightRed
import com.core.desinsystem.theme.LogiDarkGray
import com.core.desinsystem.theme.LogiOrange

// 20 - 160 * 5 = 0 - 700 (5배율)
@Composable
fun LinearHeartRateGraph(
    bpm: Int,
    modifier: Modifier = Modifier,
) {
   val off = remember(bpm) {
       when {
           bpm < 30 -> 8
           bpm > 150 -> 130
           else -> bpm - 20
       }
   }
    val x = remember(off) { off * 5f }
    val textX = remember(bpm) {
        when {
            bpm >= 100 -> x - 33
            bpm < 10 -> x - 8
            else -> x - 18
        }
    }
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier.size(
        width = 255.dp,
        height = 70.dp
    )){

        drawRoundRect(
            color = LightRed,
            topLeft = Offset(0f, 70f),
            size = Size(700f, 60f),
            cornerRadius = CornerRadius(50f)
        )

        // 정상범위 : 60-100
        drawRect(
            color = LightGreen,
            topLeft = Offset(200f, 70f),
            size = Size(200f, 60f),
        )

        drawLine(
            color = Color.Gray,
            start = Offset(200f, 70f),
            end = Offset(200f, 140f),
            strokeWidth = 3f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 10f)
        )

        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(180f, 140f),
            text = "60",
            style = TextStyle(
                color = DarkGreen,
                fontSize = 32.toSp()
            )
        )

        drawLine(
            color = Color.Gray,
            start = Offset(400f, 70f),
            end = Offset(400f, 140f),
            strokeWidth = 3f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 10f)
        )

        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(365f, 140f),
            text = "100",
            style = TextStyle(
                color = DarkGreen,
                fontSize = 32.toSp(),
            )
        )

        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(235f, 135f),
            text = "정상범위",
            style = TextStyle(
                color = DarkGreen,
                fontSize = 32.toSp()
            )
        )

        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(60f, 130f),
            text = "낮음",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 32.toSp()
            )
        )

        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(570f, 130f),
            text = "높음",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 32.toSp()
            )
        )

        
        // 70일때, 70*5 = 250
        drawText(
            textMeasurer = textMeasurer,
            topLeft = Offset(textX, 15f),
            text = "$bpm",
            style = TextStyle(
                color = LogiDarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 30.toSp(),
            )
        )
        drawLine(
            color = LogiOrange,
            start = Offset(x, 60f),
            end = Offset(x, 130f),
            strokeWidth = 3f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 10f)
        )
        drawHeart(this, Offset(x, 50f), 30f)


    }
}

private fun drawHeart(drawScope: DrawScope, center: Offset, size: Float) {
    val path = Path().apply {
        val width = size
        val height = size
        moveTo(center.x, center.y + height / 4)
        cubicTo(
            center.x - width / 2, center.y - height / 4,
            center.x - width, center.y + height / 2,
            center.x, center.y + height
        )
        cubicTo(
            center.x + width, center.y + height / 2,
            center.x + width / 2, center.y - height / 4,
            center.x, center.y + height / 4
        )
        close()
    }
    drawScope.drawPath(
        path = path,
        color = LogiOrange
    )
}

@Composable
@Preview
private fun Preview() {
    LinearHeartRateGraph(80)
}
