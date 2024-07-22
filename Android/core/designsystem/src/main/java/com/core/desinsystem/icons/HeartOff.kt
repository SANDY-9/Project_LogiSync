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

val Icons.HeartOff: ImageVector
    get() {
        if (_heartOff != null) {
            return _heartOff!!
        }
        _heartOff = Builder(name = "HeartOff", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(480.0f, 204.0f)
                quadToRelative(34.0f, -40.0f, 81.0f, -62.0f)
                reflectiveQuadToRelative(99.0f, -22.0f)
                quadToRelative(88.0f, 0.0f, 149.5f, 56.0f)
                reflectiveQuadTo(879.0f, 320.0f)
                quadToRelative(1.0f, 17.0f, -10.5f, 28.5f)
                reflectiveQuadTo(840.0f, 361.0f)
                quadToRelative(-17.0f, 1.0f, -28.5f, -10.5f)
                reflectiveQuadTo(799.0f, 322.0f)
                quadToRelative(-6.0f, -54.0f, -44.5f, -88.0f)
                reflectiveQuadTo(660.0f, 200.0f)
                quadToRelative(-40.0f, 0.0f, -75.5f, 20.0f)
                reflectiveQuadTo(529.0f, 272.0f)
                quadToRelative(-9.0f, 14.0f, -22.5f, 21.0f)
                reflectiveQuadToRelative(-27.5f, 7.0f)
                quadToRelative(-14.0f, 0.0f, -27.0f, -7.0f)
                reflectiveQuadToRelative(-21.0f, -21.0f)
                quadToRelative(-20.0f, -32.0f, -55.5f, -52.0f)
                reflectiveQuadTo(300.0f, 200.0f)
                quadToRelative(-56.0f, 0.0f, -94.5f, 34.0f)
                reflectiveQuadTo(161.0f, 322.0f)
                quadToRelative(-1.0f, 17.0f, -12.5f, 28.5f)
                reflectiveQuadTo(120.0f, 361.0f)
                quadToRelative(-17.0f, -1.0f, -28.5f, -12.5f)
                reflectiveQuadTo(81.0f, 320.0f)
                quadToRelative(8.0f, -88.0f, 69.5f, -144.0f)
                reflectiveQuadTo(300.0f, 120.0f)
                quadToRelative(52.0f, 0.0f, 99.0f, 22.0f)
                reflectiveQuadToRelative(81.0f, 62.0f)
                close()
                moveTo(480.0f, 827.0f)
                quadToRelative(-14.0f, 0.0f, -28.0f, -5.0f)
                reflectiveQuadToRelative(-25.0f, -16.0f)
                quadToRelative(-43.0f, -38.0f, -79.5f, -72.5f)
                reflectiveQuadTo(279.0f, 668.0f)
                quadToRelative(-14.0f, -14.0f, -12.5f, -29.5f)
                reflectiveQuadTo(279.0f, 612.0f)
                quadToRelative(11.0f, -11.0f, 26.5f, -12.5f)
                reflectiveQuadTo(335.0f, 611.0f)
                quadToRelative(30.0f, 29.0f, 65.5f, 62.5f)
                reflectiveQuadTo(480.0f, 746.0f)
                quadToRelative(44.0f, -39.0f, 79.5f, -72.5f)
                reflectiveQuadTo(625.0f, 611.0f)
                quadToRelative(14.0f, -14.0f, 29.5f, -11.5f)
                reflectiveQuadTo(681.0f, 613.0f)
                quadToRelative(11.0f, 11.0f, 12.0f, 27.0f)
                reflectiveQuadToRelative(-13.0f, 30.0f)
                quadToRelative(-32.0f, 30.0f, -68.5f, 64.0f)
                reflectiveQuadTo(533.0f, 806.0f)
                quadToRelative(-11.0f, 11.0f, -25.0f, 16.0f)
                reflectiveQuadToRelative(-28.0f, 5.0f)
                close()
                moveTo(442.0f, 640.0f)
                quadToRelative(13.0f, 0.0f, 22.5f, -7.5f)
                reflectiveQuadTo(478.0f, 613.0f)
                lineToRelative(54.0f, -163.0f)
                lineToRelative(35.0f, 52.0f)
                quadToRelative(5.0f, 8.0f, 14.0f, 13.0f)
                reflectiveQuadToRelative(19.0f, 5.0f)
                horizontalLineToRelative(280.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, -11.5f)
                reflectiveQuadTo(920.0f, 480.0f)
                quadToRelative(0.0f, -17.0f, -11.5f, -28.5f)
                reflectiveQuadTo(880.0f, 440.0f)
                lineTo(623.0f, 440.0f)
                lineToRelative(-69.0f, -102.0f)
                quadToRelative(-6.0f, -9.0f, -15.5f, -13.5f)
                reflectiveQuadTo(518.0f, 320.0f)
                quadToRelative(-13.0f, 0.0f, -22.5f, 7.5f)
                reflectiveQuadTo(482.0f, 347.0f)
                lineToRelative(-54.0f, 162.0f)
                lineToRelative(-34.0f, -51.0f)
                quadToRelative(-5.0f, -8.0f, -14.0f, -13.0f)
                reflectiveQuadToRelative(-19.0f, -5.0f)
                lineTo(80.0f, 440.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, 11.5f)
                reflectiveQuadTo(40.0f, 480.0f)
                quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                reflectiveQuadTo(80.0f, 520.0f)
                horizontalLineToRelative(257.0f)
                lineToRelative(69.0f, 102.0f)
                quadToRelative(6.0f, 9.0f, 15.5f, 13.5f)
                reflectiveQuadTo(442.0f, 640.0f)
                close()
                moveTo(480.0f, 473.0f)
                close()
            }
        }
        .build()
        return _heartOff!!
    }

private var _heartOff: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.HeartOff, contentDescription = "")
    }
}
