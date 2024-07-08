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

public val Icons.DevicesWearables: ImageVector
    get() {
        if (_deviceswearables != null) {
            return _deviceswearables!!
        }
        _deviceswearables = Builder(name = "deviceswearables", defaultWidth = 24.0.dp, defaultHeight
                = 24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(160.0f, 200.0f)
                horizontalLineToRelative(320.0f)
                verticalLineToRelative(-40.0f)
                lineTo(160.0f, 160.0f)
                verticalLineToRelative(40.0f)
                close()
                moveTo(160.0f, 880.0f)
                quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                reflectiveQuadTo(80.0f, 800.0f)
                verticalLineToRelative(-640.0f)
                quadToRelative(0.0f, -33.0f, 23.5f, -56.5f)
                reflectiveQuadTo(160.0f, 80.0f)
                horizontalLineToRelative(320.0f)
                quadToRelative(33.0f, 0.0f, 56.5f, 23.5f)
                reflectiveQuadTo(560.0f, 160.0f)
                verticalLineToRelative(145.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(520.0f, 345.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                reflectiveQuadTo(480.0f, 305.0f)
                verticalLineToRelative(-25.0f)
                lineTo(160.0f, 280.0f)
                verticalLineToRelative(400.0f)
                horizontalLineToRelative(233.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                reflectiveQuadTo(433.0f, 720.0f)
                quadToRelative(0.0f, 16.0f, -11.5f, 28.0f)
                reflectiveQuadTo(393.0f, 760.0f)
                lineTo(160.0f, 760.0f)
                verticalLineToRelative(40.0f)
                horizontalLineToRelative(349.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                reflectiveQuadTo(549.0f, 840.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(509.0f, 880.0f)
                lineTo(160.0f, 880.0f)
                close()
                moveTo(700.0f, 720.0f)
                quadToRelative(58.0f, 0.0f, 99.0f, -41.0f)
                reflectiveQuadToRelative(41.0f, -99.0f)
                quadToRelative(0.0f, -58.0f, -41.0f, -99.0f)
                reflectiveQuadToRelative(-99.0f, -41.0f)
                quadToRelative(-58.0f, 0.0f, -99.0f, 41.0f)
                reflectiveQuadToRelative(-41.0f, 99.0f)
                quadToRelative(0.0f, 58.0f, 41.0f, 99.0f)
                reflectiveQuadToRelative(99.0f, 41.0f)
                close()
                moveTo(640.0f, 880.0f)
                quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                reflectiveQuadTo(600.0f, 840.0f)
                verticalLineToRelative(-64.0f)
                quadToRelative(-54.0f, -27.0f, -87.0f, -79.0f)
                reflectiveQuadToRelative(-33.0f, -117.0f)
                quadToRelative(0.0f, -65.0f, 33.0f, -117.0f)
                reflectiveQuadToRelative(87.0f, -79.0f)
                verticalLineToRelative(-64.0f)
                quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                reflectiveQuadTo(640.0f, 280.0f)
                horizontalLineToRelative(120.0f)
                quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                reflectiveQuadTo(800.0f, 320.0f)
                verticalLineToRelative(64.0f)
                quadToRelative(54.0f, 27.0f, 87.0f, 79.0f)
                reflectiveQuadToRelative(33.0f, 117.0f)
                quadToRelative(0.0f, 65.0f, -33.0f, 117.0f)
                reflectiveQuadToRelative(-87.0f, 79.0f)
                verticalLineToRelative(64.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(760.0f, 880.0f)
                lineTo(640.0f, 880.0f)
                close()
                moveTo(160.0f, 760.0f)
                verticalLineToRelative(40.0f)
                verticalLineToRelative(-40.0f)
                close()
                moveTo(160.0f, 200.0f)
                verticalLineToRelative(-40.0f)
                verticalLineToRelative(40.0f)
                close()
            }
        }
        .build()
        return _deviceswearables!!
    }

private var _deviceswearables: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.DevicesWearables, contentDescription = "")
    }
}
