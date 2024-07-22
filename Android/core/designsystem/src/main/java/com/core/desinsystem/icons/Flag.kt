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

public val Icons.Flag: ImageVector
    get() {
        if (_flag != null) {
            return _flag!!
        }
        _flag = Builder(name = "Flag", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(280.0f, 560.0f)
                verticalLineToRelative(280.0f)
                quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                reflectiveQuadTo(240.0f, 880.0f)
                reflectiveQuadToRelative(-28.5f, -11.5f)
                reflectiveQuadTo(200.0f, 840.0f)
                verticalLineToRelative(-720.0f)
                quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                reflectiveQuadTo(240.0f, 80.0f)
                reflectiveQuadToRelative(28.5f, 11.5f)
                reflectiveQuadTo(280.0f, 120.0f)
                verticalLineToRelative(40.0f)
                horizontalLineToRelative(501.0f)
                quadToRelative(21.0f, 0.0f, 33.0f, 17.5f)
                reflectiveQuadToRelative(4.0f, 37.5f)
                lineToRelative(-58.0f, 145.0f)
                lineToRelative(58.0f, 145.0f)
                quadToRelative(8.0f, 20.0f, -4.0f, 37.5f)
                reflectiveQuadTo(781.0f, 560.0f)
                close()
                moveTo(500.0f, 440.0f)
                quadToRelative(33.0f, 0.0f, 56.5f, -23.5f)
                reflectiveQuadTo(580.0f, 360.0f)
                reflectiveQuadToRelative(-23.5f, -56.5f)
                reflectiveQuadTo(500.0f, 280.0f)
                reflectiveQuadToRelative(-56.5f, 23.5f)
                reflectiveQuadTo(420.0f, 360.0f)
                reflectiveQuadToRelative(23.5f, 56.5f)
                reflectiveQuadTo(500.0f, 440.0f)
            }
        }
        .build()
        return _flag!!
    }

private var _flag: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.Flag, contentDescription = "")
    }
}
