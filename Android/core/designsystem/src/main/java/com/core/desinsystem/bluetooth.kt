package com.core.desinsystem

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
import com.core.desinsystem.bluetooth

public val Icons.bluetooth: ImageVector
    get() {
        if (_bluetooth != null) {
            return _bluetooth!!
        }
        _bluetooth = Builder(name = "bluetooth", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(440.0f, 823.0f)
                verticalLineToRelative(-247.0f)
                lineTo(284.0f, 732.0f)
                quadToRelative(-11.0f, 11.0f, -28.0f, 11.0f)
                reflectiveQuadToRelative(-28.0f, -11.0f)
                quadToRelative(-11.0f, -11.0f, -11.0f, -28.0f)
                reflectiveQuadToRelative(11.0f, -28.0f)
                lineToRelative(196.0f, -196.0f)
                lineToRelative(-196.0f, -196.0f)
                quadToRelative(-11.0f, -11.0f, -11.0f, -28.0f)
                reflectiveQuadToRelative(11.0f, -28.0f)
                quadToRelative(11.0f, -11.0f, 28.0f, -11.0f)
                reflectiveQuadToRelative(28.0f, 11.0f)
                lineToRelative(156.0f, 156.0f)
                verticalLineToRelative(-247.0f)
                quadToRelative(0.0f, -18.0f, 12.0f, -29.5f)
                reflectiveQuadToRelative(28.0f, -11.5f)
                quadToRelative(8.0f, 0.0f, 15.0f, 3.0f)
                reflectiveQuadToRelative(13.0f, 9.0f)
                lineToRelative(172.0f, 172.0f)
                quadToRelative(6.0f, 6.0f, 8.5f, 13.0f)
                reflectiveQuadToRelative(2.5f, 15.0f)
                quadToRelative(0.0f, 8.0f, -2.5f, 15.0f)
                reflectiveQuadToRelative(-8.5f, 13.0f)
                lineTo(536.0f, 480.0f)
                lineToRelative(144.0f, 144.0f)
                quadToRelative(6.0f, 6.0f, 8.5f, 13.0f)
                reflectiveQuadToRelative(2.5f, 15.0f)
                quadToRelative(0.0f, 8.0f, -2.5f, 15.0f)
                reflectiveQuadToRelative(-8.5f, 13.0f)
                lineTo(508.0f, 852.0f)
                quadToRelative(-6.0f, 6.0f, -13.0f, 9.0f)
                reflectiveQuadToRelative(-15.0f, 3.0f)
                quadToRelative(-16.0f, 0.0f, -28.0f, -11.5f)
                reflectiveQuadTo(440.0f, 823.0f)
                close()
                moveTo(520.0f, 384.0f)
                lineTo(596.0f, 308.0f)
                lineTo(520.0f, 234.0f)
                verticalLineToRelative(150.0f)
                close()
                moveTo(520.0f, 726.0f)
                lineTo(596.0f, 652.0f)
                lineTo(520.0f, 576.0f)
                verticalLineToRelative(150.0f)
                close()
            }
        }
        .build()
        return _bluetooth!!
    }

private var _bluetooth: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.bluetooth, contentDescription = "")
    }
}
