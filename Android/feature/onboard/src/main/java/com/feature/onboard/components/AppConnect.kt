package com.feature.onboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.lottie.LottieConnection
import com.core.desinsystem.lottie.LottieOk
import com.core.desinsystem.theme.LogiBlue
import com.feature.onboard.R

@Composable
fun AppConnect(
    isConnectedApp: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = modifier.height(150.dp))

        Text(
            text = stringResource(id = R.string.onboard_connect_app),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = (-0.1).sp,
                fontSize = 18.sp
            ),
        )



        Spacer(modifier = modifier.height(16.dp))

        if(isConnectedApp) {
            ConnectAppOk()
        }
        else {
            NotConnectApp()
        }

        Spacer(modifier = modifier.weight(1f))

    }
}

@Composable
private fun NotConnectApp(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = modifier.size(150.dp)) {
            LottieConnection(modifier = modifier.fillMaxSize())
            Icon(
                modifier = modifier
                    .size(120.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = com.core.desinsystem.R.drawable.temp_logo),
                contentDescription = null,
                tint = Color.Gray
            )
        }

        Text(
            modifier = modifier.padding(bottom = 32.dp),
            text = stringResource(R.string.onboard_connect_app_fail1),
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = (-0.1).sp,
                fontSize = 15.sp
            ),
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = modifier.padding(bottom = 32.dp),
            text = stringResource(R.string.onboard_connect_app_fail2),
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = (-0.1).sp,
                fontSize = 15.sp
            ),
            color = LogiBlue,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ConnectAppOk(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = modifier.size(150.dp)
        ) {
            LottieOk(modifier = modifier.size(150.dp)
            )
        }

        Text(
            modifier = modifier.padding(bottom = 32.dp),
            text = stringResource(R.string.onboard_connect_app_success),
            style = MaterialTheme.typography.bodyLarge.copy(
                letterSpacing = (-0.1).sp,
                fontSize = 15.sp
            ),
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}
