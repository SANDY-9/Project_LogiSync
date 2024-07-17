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
import com.core.desinsystem.icons.Visibility

val Icons.Visibility: ImageVector
    get() {
        if (_visibility != null) {
            return _visibility!!
        }
        _visibility = Builder(name = "Visibility", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(480.0f, 640.0f)
                quadToRelative(75.0f, 0.0f, 127.5f, -52.5f)
                reflectiveQuadTo(660.0f, 460.0f)
                quadToRelative(0.0f, -75.0f, -52.5f, -127.5f)
                reflectiveQuadTo(480.0f, 280.0f)
                quadToRelative(-75.0f, 0.0f, -127.5f, 52.5f)
                reflectiveQuadTo(300.0f, 460.0f)
                quadToRelative(0.0f, 75.0f, 52.5f, 127.5f)
                reflectiveQuadTo(480.0f, 640.0f)
                close()
                moveTo(480.0f, 568.0f)
                quadToRelative(-45.0f, 0.0f, -76.5f, -31.5f)
                reflectiveQuadTo(372.0f, 460.0f)
                quadToRelative(0.0f, -45.0f, 31.5f, -76.5f)
                reflectiveQuadTo(480.0f, 352.0f)
                quadToRelative(45.0f, 0.0f, 76.5f, 31.5f)
                reflectiveQuadTo(588.0f, 460.0f)
                quadToRelative(0.0f, 45.0f, -31.5f, 76.5f)
                reflectiveQuadTo(480.0f, 568.0f)
                close()
                moveTo(480.0f, 760.0f)
                quadToRelative(-146.0f, 0.0f, -266.0f, -81.5f)
                reflectiveQuadTo(40.0f, 460.0f)
                quadToRelative(54.0f, -137.0f, 174.0f, -218.5f)
                reflectiveQuadTo(480.0f, 160.0f)
                quadToRelative(146.0f, 0.0f, 266.0f, 81.5f)
                reflectiveQuadTo(920.0f, 460.0f)
                quadToRelative(-54.0f, 137.0f, -174.0f, 218.5f)
                reflectiveQuadTo(480.0f, 760.0f)
                close()
                moveTo(480.0f, 460.0f)
                close()
                moveTo(480.0f, 680.0f)
                quadToRelative(113.0f, 0.0f, 207.5f, -59.5f)
                reflectiveQuadTo(832.0f, 460.0f)
                quadToRelative(-50.0f, -101.0f, -144.5f, -160.5f)
                reflectiveQuadTo(480.0f, 240.0f)
                quadToRelative(-113.0f, 0.0f, -207.5f, 59.5f)
                reflectiveQuadTo(128.0f, 460.0f)
                quadToRelative(50.0f, 101.0f, 144.5f, 160.5f)
                reflectiveQuadTo(480.0f, 680.0f)
                close()
            }
        }
        .build()
        return _visibility!!
    }

private var _visibility: ImageVector? = null

val Icons.Visibilityoff: ImageVector
    get() {
        if (_visibilityoff != null) {
            return _visibilityoff!!
        }
        _visibilityoff = Builder(name = "visibilityoff", defaultWidth = 24.0.dp, defaultHeight =
        24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF5f6368)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(607.0f, 333.0f)
                quadToRelative(29.0f, 29.0f, 42.5f, 66.0f)
                reflectiveQuadToRelative(9.5f, 76.0f)
                quadToRelative(0.0f, 15.0f, -11.0f, 25.5f)
                reflectiveQuadTo(622.0f, 511.0f)
                quadToRelative(-15.0f, 0.0f, -25.5f, -10.5f)
                reflectiveQuadTo(586.0f, 475.0f)
                quadToRelative(5.0f, -26.0f, -3.0f, -50.0f)
                reflectiveQuadToRelative(-25.0f, -41.0f)
                quadToRelative(-17.0f, -17.0f, -41.0f, -26.0f)
                reflectiveQuadToRelative(-51.0f, -4.0f)
                quadToRelative(-15.0f, 0.0f, -25.5f, -11.0f)
                reflectiveQuadTo(430.0f, 317.0f)
                quadToRelative(0.0f, -15.0f, 10.5f, -25.5f)
                reflectiveQuadTo(466.0f, 281.0f)
                quadToRelative(38.0f, -4.0f, 75.0f, 9.5f)
                reflectiveQuadToRelative(66.0f, 42.5f)
                close()
                moveTo(480.0f, 240.0f)
                quadToRelative(-19.0f, 0.0f, -37.0f, 1.5f)
                reflectiveQuadToRelative(-36.0f, 5.5f)
                quadToRelative(-17.0f, 3.0f, -30.5f, -5.0f)
                reflectiveQuadTo(358.0f, 218.0f)
                quadToRelative(-5.0f, -16.0f, 3.5f, -31.0f)
                reflectiveQuadToRelative(24.5f, -18.0f)
                quadToRelative(23.0f, -5.0f, 46.5f, -7.0f)
                reflectiveQuadToRelative(47.5f, -2.0f)
                quadToRelative(137.0f, 0.0f, 250.5f, 72.0f)
                reflectiveQuadTo(904.0f, 426.0f)
                quadToRelative(4.0f, 8.0f, 6.0f, 16.5f)
                reflectiveQuadToRelative(2.0f, 17.5f)
                quadToRelative(0.0f, 9.0f, -1.5f, 17.5f)
                reflectiveQuadTo(905.0f, 494.0f)
                quadToRelative(-18.0f, 40.0f, -44.5f, 75.0f)
                reflectiveQuadTo(802.0f, 633.0f)
                quadToRelative(-12.0f, 11.0f, -28.0f, 9.0f)
                reflectiveQuadToRelative(-26.0f, -16.0f)
                quadToRelative(-10.0f, -14.0f, -8.5f, -30.5f)
                reflectiveQuadTo(753.0f, 568.0f)
                quadToRelative(24.0f, -23.0f, 44.0f, -50.0f)
                reflectiveQuadToRelative(35.0f, -58.0f)
                quadToRelative(-50.0f, -101.0f, -144.5f, -160.5f)
                reflectiveQuadTo(480.0f, 240.0f)
                close()
                moveTo(480.0f, 760.0f)
                quadToRelative(-134.0f, 0.0f, -245.0f, -72.5f)
                reflectiveQuadTo(60.0f, 497.0f)
                quadToRelative(-5.0f, -8.0f, -7.5f, -17.5f)
                reflectiveQuadTo(50.0f, 460.0f)
                quadToRelative(0.0f, -10.0f, 2.0f, -19.0f)
                reflectiveQuadToRelative(7.0f, -18.0f)
                quadToRelative(20.0f, -40.0f, 46.5f, -76.5f)
                reflectiveQuadTo(166.0f, 280.0f)
                lineToRelative(-83.0f, -84.0f)
                quadToRelative(-11.0f, -12.0f, -10.5f, -28.5f)
                reflectiveQuadTo(84.0f, 140.0f)
                quadToRelative(11.0f, -11.0f, 28.0f, -11.0f)
                reflectiveQuadToRelative(28.0f, 11.0f)
                lineToRelative(680.0f, 680.0f)
                quadToRelative(11.0f, 11.0f, 11.5f, 27.5f)
                reflectiveQuadTo(820.0f, 876.0f)
                quadToRelative(-11.0f, 11.0f, -28.0f, 11.0f)
                reflectiveQuadToRelative(-28.0f, -11.0f)
                lineTo(624.0f, 738.0f)
                quadToRelative(-35.0f, 11.0f, -71.0f, 16.5f)
                reflectiveQuadToRelative(-73.0f, 5.5f)
                close()
                moveTo(222.0f, 336.0f)
                quadToRelative(-29.0f, 26.0f, -53.0f, 57.0f)
                reflectiveQuadToRelative(-41.0f, 67.0f)
                quadToRelative(50.0f, 101.0f, 144.5f, 160.5f)
                reflectiveQuadTo(480.0f, 680.0f)
                quadToRelative(20.0f, 0.0f, 39.0f, -2.5f)
                reflectiveQuadToRelative(39.0f, -5.5f)
                lineToRelative(-36.0f, -38.0f)
                quadToRelative(-11.0f, 3.0f, -21.0f, 4.5f)
                reflectiveQuadToRelative(-21.0f, 1.5f)
                quadToRelative(-75.0f, 0.0f, -127.5f, -52.5f)
                reflectiveQuadTo(300.0f, 460.0f)
                quadToRelative(0.0f, -11.0f, 1.5f, -21.0f)
                reflectiveQuadToRelative(4.5f, -21.0f)
                lineToRelative(-84.0f, -82.0f)
                close()
                moveTo(541.0f, 429.0f)
                close()
                moveTo(390.0f, 504.0f)
                close()
            }
        }
            .build()
        return _visibilityoff!!
    }

private var _visibilityoff: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = Icons.Visibility, contentDescription = "")
    }
}
