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

public val Icons.AdminOn: ImageVector
    get() {
        if (_adminOn != null) {
            return _adminOn!!
        }
        _adminOn = Builder(name = "AdminOn", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(680.0f, 841.0f)
                quadToRelative(-8.0f, 0.0f, -16.0f, -2.0f)
                reflectiveQuadToRelative(-15.0f, -7.0f)
                lineToRelative(-120.0f, -70.0f)
                quadToRelative(-14.0f, -8.0f, -21.5f, -21.5f)
                reflectiveQuadTo(500.0f, 711.0f)
                verticalLineToRelative(-141.0f)
                quadToRelative(0.0f, -16.0f, 7.5f, -29.5f)
                reflectiveQuadTo(529.0f, 519.0f)
                lineToRelative(120.0f, -70.0f)
                quadToRelative(7.0f, -5.0f, 15.0f, -7.0f)
                reflectiveQuadToRelative(16.0f, -2.0f)
                quadToRelative(8.0f, 0.0f, 15.5f, 2.5f)
                reflectiveQuadTo(710.0f, 449.0f)
                lineToRelative(120.0f, 70.0f)
                quadToRelative(14.0f, 8.0f, 22.0f, 21.5f)
                reflectiveQuadToRelative(8.0f, 29.5f)
                verticalLineToRelative(141.0f)
                quadToRelative(0.0f, 16.0f, -8.0f, 29.5f)
                reflectiveQuadTo(830.0f, 762.0f)
                lineToRelative(-120.0f, 70.0f)
                quadToRelative(-7.0f, 4.0f, -14.5f, 6.5f)
                reflectiveQuadTo(680.0f, 841.0f)
                close()
                moveTo(160.0f, 800.0f)
                quadToRelative(-33.0f, 0.0f, -56.5f, -23.5f)
                reflectiveQuadTo(80.0f, 720.0f)
                verticalLineToRelative(-32.0f)
                quadToRelative(0.0f, -33.0f, 17.0f, -62.0f)
                reflectiveQuadToRelative(47.0f, -44.0f)
                quadToRelative(56.0f, -28.0f, 117.0f, -44.5f)
                reflectiveQuadTo(385.0f, 519.0f)
                quadToRelative(8.0f, 0.0f, 14.0f, 3.5f)
                reflectiveQuadToRelative(10.0f, 9.5f)
                quadToRelative(4.0f, 6.0f, 5.0f, 13.5f)
                reflectiveQuadToRelative(-1.0f, 15.5f)
                quadToRelative(-6.0f, 20.0f, -9.0f, 40.5f)
                reflectiveQuadToRelative(-3.0f, 40.5f)
                quadToRelative(0.0f, 30.0f, 6.5f, 59.5f)
                reflectiveQuadTo(427.0f, 759.0f)
                quadToRelative(3.0f, 8.0f, 3.0f, 15.0f)
                reflectiveQuadToRelative(-4.0f, 13.0f)
                quadToRelative(-4.0f, 6.0f, -9.5f, 9.5f)
                reflectiveQuadTo(403.0f, 800.0f)
                lineTo(160.0f, 800.0f)
                close()
                moveTo(400.0f, 480.0f)
                quadToRelative(-66.0f, 0.0f, -113.0f, -47.0f)
                reflectiveQuadToRelative(-47.0f, -113.0f)
                quadToRelative(0.0f, -66.0f, 47.0f, -113.0f)
                reflectiveQuadToRelative(113.0f, -47.0f)
                quadToRelative(66.0f, 0.0f, 113.0f, 47.0f)
                reflectiveQuadToRelative(47.0f, 113.0f)
                quadToRelative(0.0f, 66.0f, -47.0f, 113.0f)
                reflectiveQuadToRelative(-113.0f, 47.0f)
                close()
                moveTo(586.0f, 554.0f)
                lineTo(680.0f, 609.0f)
                lineTo(774.0f, 554.0f)
                lineTo(680.0f, 500.0f)
                lineTo(586.0f, 554.0f)
                close()
                moveTo(710.0f, 762.0f)
                lineTo(800.0f, 710.0f)
                verticalLineToRelative(-110.0f)
                lineToRelative(-90.0f, 53.0f)
                verticalLineToRelative(109.0f)
                close()
                moveTo(560.0f, 710.0f)
                lineTo(650.0f, 763.0f)
                verticalLineToRelative(-109.0f)
                lineToRelative(-90.0f, -53.0f)
                verticalLineToRelative(109.0f)
                close()
            }
        }
        .build()
        return _adminOn!!
    }

private var _adminOn: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.AdminOn, contentDescription = "")
    }
}
