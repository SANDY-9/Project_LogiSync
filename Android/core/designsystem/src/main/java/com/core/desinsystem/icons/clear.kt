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

public val Icons.Clear: ImageVector
    get() {
        if (_clear != null) {
            return _clear!!
        }
        _clear = Builder(name = "clear", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveToRelative(480.0f, 536.0f)
                lineToRelative(116.0f, 116.0f)
                quadToRelative(11.0f, 11.0f, 28.0f, 11.0f)
                reflectiveQuadToRelative(28.0f, -11.0f)
                reflectiveQuadToRelative(11.0f, -28.0f)
                reflectiveQuadToRelative(-11.0f, -28.0f)
                lineTo(536.0f, 480.0f)
                lineToRelative(116.0f, -116.0f)
                quadToRelative(11.0f, -11.0f, 11.0f, -28.0f)
                reflectiveQuadToRelative(-11.0f, -28.0f)
                reflectiveQuadToRelative(-28.0f, -11.0f)
                reflectiveQuadToRelative(-28.0f, 11.0f)
                lineTo(480.0f, 424.0f)
                lineTo(364.0f, 308.0f)
                quadToRelative(-11.0f, -11.0f, -28.0f, -11.0f)
                reflectiveQuadToRelative(-28.0f, 11.0f)
                reflectiveQuadToRelative(-11.0f, 28.0f)
                reflectiveQuadToRelative(11.0f, 28.0f)
                lineToRelative(116.0f, 116.0f)
                lineToRelative(-116.0f, 116.0f)
                quadToRelative(-11.0f, 11.0f, -11.0f, 28.0f)
                reflectiveQuadToRelative(11.0f, 28.0f)
                reflectiveQuadToRelative(28.0f, 11.0f)
                reflectiveQuadToRelative(28.0f, -11.0f)
                close()
                moveTo(480.0f, 880.0f)
                quadToRelative(-83.0f, 0.0f, -156.0f, -31.5f)
                reflectiveQuadTo(197.0f, 763.0f)
                reflectiveQuadToRelative(-85.5f, -127.0f)
                reflectiveQuadTo(80.0f, 480.0f)
                reflectiveQuadToRelative(31.5f, -156.0f)
                reflectiveQuadTo(197.0f, 197.0f)
                reflectiveQuadToRelative(127.0f, -85.5f)
                reflectiveQuadTo(480.0f, 80.0f)
                reflectiveQuadToRelative(156.0f, 31.5f)
                reflectiveQuadTo(763.0f, 197.0f)
                reflectiveQuadToRelative(85.5f, 127.0f)
                reflectiveQuadTo(880.0f, 480.0f)
                reflectiveQuadToRelative(-31.5f, 156.0f)
                reflectiveQuadTo(763.0f, 763.0f)
                reflectiveQuadToRelative(-127.0f, 85.5f)
                reflectiveQuadTo(480.0f, 880.0f)
                moveToRelative(0.0f, -80.0f)
                quadToRelative(134.0f, 0.0f, 227.0f, -93.0f)
                reflectiveQuadToRelative(93.0f, -227.0f)
                reflectiveQuadToRelative(-93.0f, -227.0f)
                reflectiveQuadToRelative(-227.0f, -93.0f)
                reflectiveQuadToRelative(-227.0f, 93.0f)
                reflectiveQuadToRelative(-93.0f, 227.0f)
                reflectiveQuadToRelative(93.0f, 227.0f)
                reflectiveQuadToRelative(227.0f, 93.0f)
                moveToRelative(0.0f, -320.0f)
            }
        }
        .build()
        return _clear!!
    }

private var _clear: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.Clear, contentDescription = "")
    }
}
