package com.feature.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.feature.onboard.components.BluetoothPhase
import com.sandy.designsystem.common.NextButton

@Composable
fun OnboardingScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OnboardingAppBar(
            onNavigate = { navController.navigateUp() }
        )
        OnboardingContent(
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
private fun OnboardingAppBar(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        IconButton(
            modifier = modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterStart),
            onClick = onNavigate
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun OnboardingContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.onboard_connect_title1),
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = stringResource(id = R.string.onboard_connect_title2),
            style = MaterialTheme.typography.headlineSmall,
        )

        BluetoothPhase()
    }
    NextButton(
        title = stringResource(id = R.string.onboard_button),
        onClick = { /*TODO*/ }
    )
}
