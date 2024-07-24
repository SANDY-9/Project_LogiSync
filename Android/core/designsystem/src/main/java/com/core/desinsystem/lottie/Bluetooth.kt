package com.core.desinsystem.lottie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable
fun LottieBluetooth(modifier: Modifier = Modifier) {
    val dotLottieController = remember { DotLottieController() }
    DotLottieAnimation(
        modifier = modifier,
        source = DotLottieSource.Asset("bluetooth.json"),
        autoplay = true,
        loop = true,
        speed = 0.7f,
        useFrameInterpolation = false,
        playMode = Mode.FORWARD,
        controller = dotLottieController
    )
}

@Composable
fun LottieBluetoothOk(modifier: Modifier = Modifier) {
    val dotLottieController = remember { DotLottieController() }
    Box(
        modifier = modifier
    ) {
        DotLottieAnimation(
            modifier = Modifier.padding(20.dp).fillMaxSize(),
            source = DotLottieSource.Asset("bluetooth_ok.json"),
            autoplay = true,
            loop = true,
            speed = 1f,
            useFrameInterpolation = false,
            playMode = Mode.BOUNCE,
            controller = dotLottieController
        )
    }
}
