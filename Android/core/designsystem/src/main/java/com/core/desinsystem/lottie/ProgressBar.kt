package com.core.desinsystem.lottie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable
fun LottieProgressBarBlue(modifier: Modifier = Modifier) {
    val dotLottieController = remember { DotLottieController() }
    Box(modifier = modifier) {
        DotLottieAnimation(
            modifier = Modifier.size(50.dp).align(Alignment.Center),
            source = DotLottieSource.Asset("progress_blue.json"),
            autoplay = true,
            loop = true,
            speed = 0.7f,
            useFrameInterpolation = false,
            playMode = Mode.FORWARD,
            controller = dotLottieController
        )
    }
}
