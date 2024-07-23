package com.core.desinsystem.lottie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable
fun LottieOk(modifier: Modifier = Modifier) {
    val dotLottieController = remember { DotLottieController() }
    DotLottieAnimation(
        modifier = modifier,
        source = DotLottieSource.Asset("ok.json"),
        autoplay = true,
        loop = true,
        speed = 1f,
        useFrameInterpolation = false,
        playMode = Mode.FORWARD,
        controller = dotLottieController
    )
}
