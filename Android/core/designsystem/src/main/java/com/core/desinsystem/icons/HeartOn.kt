package com.core.desinsystem.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Icons.HeartOn: ImageVector
    get() {
        if (_heartOn != null) {
            return _heartOn!!
        }
        _heartOn = Builder(name = "HeartOn", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveToRelative(428.0f, 509.0f)
                lineToRelative(-34.0f, -51.0f)
                quadToRelative(-5.0f, -8.0f, -14.0f, -13.0f)
                reflectiveQuadToRelative(-19.0f, -5.0f)
                lineTo(124.0f, 440.0f)
                quadToRelative(-11.0f, 0.0f, -20.0f, -6.5f)
                reflectiveQuadTo(91.0f, 416.0f)
                quadToRelative(-6.0f, -19.0f, -8.5f, -37.5f)
                reflectiveQuadTo(80.0f, 340.0f)
                quadToRelative(0.0f, -94.0f, 63.0f, -157.0f)
                reflectiveQuadToRelative(157.0f, -63.0f)
                quadToRelative(52.0f, 0.0f, 99.0f, 22.0f)
                reflectiveQuadToRelative(81.0f, 62.0f)
                quadToRelative(34.0f, -40.0f, 81.0f, -62.0f)
                reflectiveQuadToRelative(99.0f, -22.0f)
                quadToRelative(94.0f, 0.0f, 157.0f, 63.0f)
                reflectiveQuadToRelative(63.0f, 157.0f)
                quadToRelative(0.0f, 20.0f, -2.5f, 38.5f)
                reflectiveQuadTo(869.0f, 416.0f)
                quadToRelative(-3.0f, 11.0f, -12.5f, 17.5f)
                reflectiveQuadTo(836.0f, 440.0f)
                lineTo(623.0f, 440.0f)
                lineToRelative(-69.0f, -102.0f)
                quadToRelative(-6.0f, -9.0f, -15.5f, -13.5f)
                reflectiveQuadTo(518.0f, 320.0f)
                quadToRelative(-13.0f, 0.0f, -22.5f, 7.5f)
                reflectiveQuadTo(482.0f, 347.0f)
                lineToRelative(-54.0f, 162.0f)
                close()
                moveTo(480.0f, 827.0f)
                quadToRelative(-14.0f, 0.0f, -28.0f, -5.0f)
                reflectiveQuadToRelative(-25.0f, -16.0f)
                quadToRelative(-94.0f, -84.0f, -159.0f, -147.5f)
                reflectiveQuadTo(163.0f, 544.0f)
                quadToRelative(-6.0f, -8.0f, -1.5f, -16.0f)
                reflectiveQuadToRelative(13.5f, -8.0f)
                horizontalLineToRelative(162.0f)
                lineToRelative(69.0f, 102.0f)
                quadToRelative(6.0f, 9.0f, 15.5f, 13.5f)
                reflectiveQuadTo(442.0f, 640.0f)
                quadToRelative(13.0f, 0.0f, 22.5f, -7.5f)
                reflectiveQuadTo(478.0f, 613.0f)
                lineToRelative(54.0f, -163.0f)
                lineToRelative(35.0f, 52.0f)
                quadToRelative(5.0f, 8.0f, 14.0f, 13.0f)
                reflectiveQuadToRelative(19.0f, 5.0f)
                horizontalLineToRelative(185.0f)
                quadToRelative(9.0f, 0.0f, 13.5f, 8.0f)
                reflectiveQuadToRelative(-1.5f, 16.0f)
                quadToRelative(-40.0f, 51.0f, -105.0f, 114.5f)
                reflectiveQuadTo(533.0f, 806.0f)
                quadToRelative(-11.0f, 11.0f, -25.0f, 16.0f)
                reflectiveQuadToRelative(-28.0f, 5.0f)
                close()
            }
        }
        .build()
        return _heartOn!!
    }

private var _heartOn: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.HeartOn, contentDescription = "")
    }
}
