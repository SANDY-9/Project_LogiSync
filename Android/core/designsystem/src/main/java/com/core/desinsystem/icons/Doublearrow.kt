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

public val Icons.DoubleArrow: ImageVector
    get() {
        if (_doublearrow != null) {
            return _doublearrow!!
        }
        _doublearrow = Builder(
            name = "Doublearrow", 
            defaultWidth = 24.0.dp, 
            defaultHeight = 24.0.dp, 
            viewportWidth = 960.0f, 
            viewportHeight = 960.0f
            ).apply {
                path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveToRelative(313.0f, 480.0f)
                    lineToRelative(155.0f, 156.0f)
                    quadToRelative(11.0f, 11.0f, 11.5f, 27.5f)
                    reflectiveQuadTo(468.0f, 692.0f)
                    quadToRelative(-11.0f, 11.0f, -28.0f, 11.0f)
                    reflectiveQuadToRelative(-28.0f, -11.0f)
                    lineTo(228.0f, 508.0f)
                    quadToRelative(-6.0f, -6.0f, -8.5f, -13.0f)
                    reflectiveQuadToRelative(-2.5f, -15.0f)
                    quadToRelative(0.0f, -8.0f, 2.5f, -15.0f)
                    reflectiveQuadToRelative(8.5f, -13.0f)
                    lineToRelative(184.0f, -184.0f)
                    quadToRelative(11.0f, -11.0f, 27.5f, -11.5f)
                    reflectiveQuadTo(468.0f, 268.0f)
                    quadToRelative(11.0f, 11.0f, 11.0f, 28.0f)
                    reflectiveQuadToRelative(-11.0f, 28.0f)
                    lineTo(313.0f, 480.0f)
                    close()
                    moveTo(577.0f, 480.0f)
                    lineTo(732.0f, 636.0f)
                    quadToRelative(11.0f, 11.0f, 11.5f, 27.5f)
                    reflectiveQuadTo(732.0f, 692.0f)
                    quadToRelative(-11.0f, 11.0f, -28.0f, 11.0f)
                    reflectiveQuadToRelative(-28.0f, -11.0f)
                    lineTo(492.0f, 508.0f)
                    quadToRelative(-6.0f, -6.0f, -8.5f, -13.0f)
                    reflectiveQuadToRelative(-2.5f, -15.0f)
                    quadToRelative(0.0f, -8.0f, 2.5f, -15.0f)
                    reflectiveQuadToRelative(8.5f, -13.0f)
                    lineToRelative(184.0f, -184.0f)
                    quadToRelative(11.0f, -11.0f, 27.5f, -11.5f)
                    reflectiveQuadTo(732.0f, 268.0f)
                    quadToRelative(11.0f, 11.0f, 11.0f, 28.0f)
                    reflectiveQuadToRelative(-11.0f, 28.0f)
                    lineTo(577.0f, 480.0f)
                    close()
                }
            }
            .build()
            return _doublearrow!!
        }

    private var _doublearrow: ImageVector? = null

    @Preview
    @Composable
    private fun Preview() {
        Box(modifier = Modifier.padding(12.dp)) {
            Image(imageVector = Icons.DoubleArrow, contentDescription = "")
        }
    }
