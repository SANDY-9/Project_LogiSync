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

public val Icons.Page: ImageVector
    get() {
        if (_page != null) {
            return _page!!
        }
        _page = Builder(
            name = "Page", 
            defaultWidth = 24.0.dp, 
            defaultHeight = 24.0.dp, 
            viewportWidth = 960.0f, 
            viewportHeight = 960.0f
            ).apply {
                path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(360.0f, 720.0f)
                    horizontalLineToRelative(240.0f)
                    quadToRelative(17.0f, 0.0f, 28.5f, -11.5f)
                    reflectiveQuadTo(640.0f, 680.0f)
                    quadToRelative(0.0f, -17.0f, -11.5f, -28.5f)
                    reflectiveQuadTo(600.0f, 640.0f)
                    lineTo(360.0f, 640.0f)
                    quadToRelative(-17.0f, 0.0f, -28.5f, 11.5f)
                    reflectiveQuadTo(320.0f, 680.0f)
                    quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                    reflectiveQuadTo(360.0f, 720.0f)
                    close()
                    moveTo(360.0f, 560.0f)
                    horizontalLineToRelative(240.0f)
                    quadToRelative(17.0f, 0.0f, 28.5f, -11.5f)
                    reflectiveQuadTo(640.0f, 520.0f)
                    quadToRelative(0.0f, -17.0f, -11.5f, -28.5f)
                    reflectiveQuadTo(600.0f, 480.0f)
                    lineTo(360.0f, 480.0f)
                    quadToRelative(-17.0f, 0.0f, -28.5f, 11.5f)
                    reflectiveQuadTo(320.0f, 520.0f)
                    quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                    reflectiveQuadTo(360.0f, 560.0f)
                    close()
                    moveTo(240.0f, 880.0f)
                    quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                    reflectiveQuadTo(160.0f, 800.0f)
                    verticalLineToRelative(-640.0f)
                    quadToRelative(0.0f, -33.0f, 23.5f, -56.5f)
                    reflectiveQuadTo(240.0f, 80.0f)
                    horizontalLineToRelative(287.0f)
                    quadToRelative(16.0f, 0.0f, 30.5f, 6.0f)
                    reflectiveQuadToRelative(25.5f, 17.0f)
                    lineToRelative(194.0f, 194.0f)
                    quadToRelative(11.0f, 11.0f, 17.0f, 25.5f)
                    reflectiveQuadToRelative(6.0f, 30.5f)
                    verticalLineToRelative(447.0f)
                    quadToRelative(0.0f, 33.0f, -23.5f, 56.5f)
                    reflectiveQuadTo(720.0f, 880.0f)
                    lineTo(240.0f, 880.0f)
                    close()
                    moveTo(520.0f, 320.0f)
                    quadToRelative(0.0f, 17.0f, 11.5f, 28.5f)
                    reflectiveQuadTo(560.0f, 360.0f)
                    horizontalLineToRelative(160.0f)
                    lineTo(520.0f, 160.0f)
                    verticalLineToRelative(160.0f)
                    close()
                }
            }
            .build()
            return _page!!
        }

    private var _page: ImageVector? = null

    @Preview
    @Composable
    private fun Preview() {
        Box(modifier = Modifier.padding(12.dp)) {
            Image(imageVector = Icons.Page, contentDescription = "")
        }
    }
