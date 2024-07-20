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

val Icons.PulseAlert: ImageVector
    get() {
        if (_pulseAlert != null) {
            return _pulseAlert!!
        }
        _pulseAlert = Builder(name = "PulseAlert", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(680.0f, 520.0f)
                verticalLineToRelative(-120.0f)
                quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                reflectiveQuadTo(720.0f, 360.0f)
                reflectiveQuadToRelative(28.5f, 11.5f)
                reflectiveQuadTo(760.0f, 400.0f)
                verticalLineToRelative(120.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(720.0f, 560.0f)
                reflectiveQuadToRelative(-28.5f, -11.5f)
                reflectiveQuadTo(680.0f, 520.0f)
                moveToRelative(-559.0f, -40.0f)
                quadToRelative(-22.0f, -35.0f, -31.5f, -69.5f)
                reflectiveQuadTo(80.0f, 339.0f)
                quadToRelative(0.0f, -94.0f, 63.0f, -156.5f)
                reflectiveQuadTo(300.0f, 120.0f)
                quadToRelative(51.0f, 0.0f, 98.5f, 22.0f)
                reflectiveQuadToRelative(81.5f, 62.0f)
                quadToRelative(34.0f, -40.0f, 81.0f, -62.0f)
                reflectiveQuadToRelative(99.0f, -22.0f)
                quadToRelative(94.0f, 0.0f, 157.0f, 63.5f)
                reflectiveQuadTo(880.0f, 341.0f)
                quadToRelative(-32.0f, -29.0f, -73.0f, -45.0f)
                reflectiveQuadToRelative(-87.0f, -16.0f)
                quadToRelative(-90.0f, 0.0f, -156.0f, 57.5f)
                reflectiveQuadTo(484.0f, 480.0f)
                horizontalLineToRelative(-62.0f)
                lineToRelative(-68.0f, -102.0f)
                quadToRelative(-6.0f, -9.0f, -14.5f, -13.5f)
                reflectiveQuadTo(321.0f, 360.0f)
                reflectiveQuadToRelative(-19.0f, 4.5f)
                reflectiveQuadToRelative(-15.0f, 13.5f)
                lineToRelative(-69.0f, 102.0f)
                close()
                moveTo(353.0f, 726.0f)
                quadToRelative(-61.0f, -55.0f, -101.5f, -94.5f)
                reflectiveQuadTo(182.0f, 560.0f)
                horizontalLineToRelative(58.0f)
                quadToRelative(10.0f, 0.0f, 19.0f, -5.0f)
                reflectiveQuadToRelative(14.0f, -13.0f)
                lineToRelative(47.0f, -70.0f)
                lineToRelative(47.0f, 70.0f)
                quadToRelative(5.0f, 8.0f, 14.0f, 13.0f)
                reflectiveQuadToRelative(19.0f, 5.0f)
                horizontalLineToRelative(84.0f)
                quadToRelative(9.0f, 55.0f, 40.5f, 99.0f)
                reflectiveQuadToRelative(78.5f, 70.0f)
                lineToRelative(-70.0f, 63.0f)
                quadToRelative(-11.0f, 10.0f, -25.0f, 15.0f)
                reflectiveQuadToRelative(-28.0f, 5.0f)
                reflectiveQuadToRelative(-28.0f, -5.0f)
                reflectiveQuadToRelative(-25.0f, -15.0f)
                close()
                moveTo(720.0f, 680.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                reflectiveQuadTo(680.0f, 640.0f)
                reflectiveQuadToRelative(11.5f, -28.5f)
                reflectiveQuadTo(720.0f, 600.0f)
                reflectiveQuadToRelative(28.5f, 11.5f)
                reflectiveQuadTo(760.0f, 640.0f)
                reflectiveQuadToRelative(-11.5f, 28.5f)
                reflectiveQuadTo(720.0f, 680.0f)
            }
        }
        .build()
        return _pulseAlert!!
    }

private var _pulseAlert: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.PulseAlert, contentDescription = "")
    }
}
