package com.feature.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.BoxLayout
import com.feature.home.components.HeartRateInfo
import com.feature.home.components.PairingInfo
import com.feature.home.components.Profile
import com.feature.home.components.ReportInfo
import com.feature.home.components.ReportItem
import com.feature.home.model.HomeUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    // DisposableEffect
    val context = LocalContext.current as? Activity?
    HomeWearableListener(context)
    BackHandler(enabled = true) {
        context?.finish()
    }

    // Ui
    val state: HomeUiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_PAUSE) {
        viewModel.stopWearableConnectMonitoring()
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.restartWearableConnectMonitoring()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        stickyHeader {
            HomeAppBar()
        }

        state.account?.let {
            item {
                BoxLayout {
                    Profile(
                        dateStr = state.date,
                        account = it,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                }
            }
        }

        item {
            BoxLayout {
                PairingInfo(
                    deviceName = state.pairedDeviceName,
                    isPairedWatch = state.isPairedWatch,
                )
                Spacer(modifier = modifier.height(16.dp))
            }
        }

        item {
            BoxLayout {
                HeartRateInfo(
                    heartRate = state.heartRate,
                    onRequestCollect = {},
                )
                Spacer(modifier = modifier.height(16.dp))
            }
        }

        item {
            BoxLayout {
                ReportInfo(state.emptyReport)
            }
        }

        items(count = 5) {
            BoxLayout(
                padding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 8.dp)
            ) {
                ReportItem()
            }
        }

    }
}

@Composable
private fun HomeAppBar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Image(
            modifier = modifier
                .size(width = 230.dp, height = 80.dp)
                .padding(16.dp)
                .align(Alignment.CenterStart),
            painter = painterResource(id = com.core.desinsystem.R.drawable.temp_logo_wide),
            contentDescription = null,
        )

        IconButton(
            modifier = modifier
                .padding(end = 4.dp, top = 8.dp)
                .align(Alignment.TopEnd),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = null,
            )
        }
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}
