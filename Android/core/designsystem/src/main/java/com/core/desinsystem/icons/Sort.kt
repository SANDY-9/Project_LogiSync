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

public val Icons.Sort: ImageVector
    get() {
        if (_sort != null) {
            return _sort!!
        }
        _sort = Builder(
            name = "Sort", 
            defaultWidth = 24.0.dp, 
            defaultHeight = 24.0.dp, 
            viewportWidth = 960.0f, 
            viewportHeight = 960.0f
            ).apply {
                path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(160.0f, 720.0f)
                    quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                    reflectiveQuadTo(120.0f, 680.0f)
                    quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                    reflectiveQuadTo(160.0f, 640.0f)
                    horizontalLineToRelative(160.0f)
                    quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                    reflectiveQuadTo(360.0f, 680.0f)
                    quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                    reflectiveQuadTo(320.0f, 720.0f)
                    lineTo(160.0f, 720.0f)
                    close()
                    moveTo(160.0f, 520.0f)
                    quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                    reflectiveQuadTo(120.0f, 480.0f)
                    quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                    reflectiveQuadTo(160.0f, 440.0f)
                    horizontalLineToRelative(400.0f)
                    quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                    reflectiveQuadTo(600.0f, 480.0f)
                    quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                    reflectiveQuadTo(560.0f, 520.0f)
                    lineTo(160.0f, 520.0f)
                    close()
                    moveTo(160.0f, 320.0f)
                    quadToRelative(-17.0f, 0.0f, -28.5f, -11.5f)
                    reflectiveQuadTo(120.0f, 280.0f)
                    quadToRelative(0.0f, -17.0f, 11.5f, -28.5f)
                    reflectiveQuadTo(160.0f, 240.0f)
                    horizontalLineToRelative(640.0f)
                    quadToRelative(17.0f, 0.0f, 28.5f, 11.5f)
                    reflectiveQuadTo(840.0f, 280.0f)
                    quadToRelative(0.0f, 17.0f, -11.5f, 28.5f)
                    reflectiveQuadTo(800.0f, 320.0f)
                    lineTo(160.0f, 320.0f)
                    close()
                }
            }
            .build()
            return _sort!!
        }

    private var _sort: ImageVector? = null

    @Preview
    @Composable
    private fun Preview() {
        Box(modifier = Modifier.padding(12.dp)) {
            Image(imageVector = Icons.Sort, contentDescription = "")
        }
    }
