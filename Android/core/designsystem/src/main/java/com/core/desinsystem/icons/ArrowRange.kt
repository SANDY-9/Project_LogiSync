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

public val Icons.ArrowRange: ImageVector
    get() {
        if (_arrowRange != null) {
            return _arrowRange!!
        }
        _arrowRange = Builder(
            name = "ArrowRange", 
            defaultWidth = 24.0.dp, 
            defaultHeight = 24.0.dp, 
            viewportWidth = 960.0f, 
            viewportHeight = 960.0f
            ).apply {
                path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveToRelative(233.0f, 520.0f)
                    lineToRelative(75.0f, 75.0f)
                    quadToRelative(11.0f, 12.0f, 11.5f, 28.5f)
                    reflectiveQuadTo(308.0f, 652.0f)
                    quadToRelative(-12.0f, 12.0f, -28.0f, 12.0f)
                    reflectiveQuadToRelative(-28.0f, -12.0f)
                    lineTo(108.0f, 508.0f)
                    quadToRelative(-6.0f, -6.0f, -8.5f, -13.0f)
                    reflectiveQuadTo(97.0f, 480.0f)
                    quadToRelative(0.0f, -8.0f, 2.5f, -15.0f)
                    reflectiveQuadToRelative(8.5f, -13.0f)
                    lineToRelative(144.0f, -144.0f)
                    quadToRelative(12.0f, -12.0f, 28.0f, -12.0f)
                    reflectiveQuadToRelative(28.0f, 12.0f)
                    quadToRelative(12.0f, 12.0f, 12.0f, 28.5f)
                    reflectiveQuadTo(308.0f, 365.0f)
                    lineToRelative(-75.0f, 75.0f)
                    horizontalLineToRelative(494.0f)
                    lineToRelative(-75.0f, -75.0f)
                    quadToRelative(-11.0f, -12.0f, -11.5f, -28.5f)
                    reflectiveQuadTo(652.0f, 308.0f)
                    quadToRelative(12.0f, -12.0f, 28.0f, -12.0f)
                    reflectiveQuadToRelative(28.0f, 12.0f)
                    lineToRelative(144.0f, 144.0f)
                    quadToRelative(6.0f, 6.0f, 8.5f, 13.0f)
                    reflectiveQuadToRelative(2.5f, 15.0f)
                    quadToRelative(0.0f, 8.0f, -2.5f, 15.0f)
                    reflectiveQuadToRelative(-8.5f, 13.0f)
                    lineTo(708.0f, 652.0f)
                    quadToRelative(-12.0f, 12.0f, -28.0f, 12.0f)
                    reflectiveQuadToRelative(-28.0f, -12.0f)
                    quadToRelative(-12.0f, -12.0f, -12.0f, -28.5f)
                    reflectiveQuadToRelative(12.0f, -28.5f)
                    lineToRelative(75.0f, -75.0f)
                    lineTo(233.0f, 520.0f)
                    close()
                }
            }
            .build()
            return _arrowRange!!
        }

    private var _arrowRange: ImageVector? = null

    @Preview
    @Composable
    private fun Preview() {
        Box(modifier = Modifier.padding(12.dp)) {
            Image(imageVector = Icons.ArrowRange, contentDescription = "")
        }
    }
