package com.sandy.logisync.presentation.ui.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.sandy.logisync.R

@Composable
fun HeartRateCollecting(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        DotLottieAnimation(
            modifier = modifier.align(Alignment.Center),
            source = DotLottieSource.Asset("heart_rate.json"),
            autoplay = true,
            loop = true,
            speed = 3f,
            useFrameInterpolation = false,
            playMode = Mode.FORWARD,
        )
        Text(
            modifier = modifier.align(Alignment.BottomCenter).padding(bottom = 30.dp),
            text = stringResource(id = R.string.heart_rate_collecting1),
            color = Color.White,
            style = MaterialTheme.typography.caption2
        )
    }
}
@Preview(name = "HeartRateCollecting")
@Composable
private fun PreviewHeartRateCollecting() {
    HeartRateCollecting()
}
