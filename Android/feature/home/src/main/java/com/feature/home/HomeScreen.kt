package com.feature.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.core.desinsystem.lottie.LottieProgressBarBlue
import com.core.model.Account
import com.core.model.Arrest
import com.core.navigation.Args
import com.core.navigation.Route
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
    val context = LocalContext.current
    HomeWearableListener(context)
    BackOnPressed(context)

    // Ui
    val state: HomeUiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_PAUSE) {
        viewModel.stopWearableConnectMonitoring()
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.restartWearableConnectMonitoring()
    }

    Column {
        Box(modifier = modifier.height(215.dp)) {
            Image(
                painter = painterResource(
                    id = com.core.desinsystem.R.drawable.bg_white_half
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                alpha = 0.5f
            )
            HomeAppBar(state.date, state.account)
        }
        if(!state.loading) {
            HomeContent(
                state = state,
                onRequestCollect = viewModel::requestCollectHeartBeat,
                onAllReport = {
                    navController.run {
                        currentBackStackEntry?.savedStateHandle?.set(Args.ID, state.account?.id)
                        navigate(Route.Arrest.route)
                    }
                },
                onItemClick = { arrest ->
                    navController.run {
                        currentBackStackEntry?.savedStateHandle?.set(Args.ARREST, arrest)
                        navigate(Route.ArrestDetails.route)
                    }
                }
            )
        }
        else LottieProgressBarBlue(modifier = modifier.fillMaxSize())
    }
}


@Composable
private fun HomeContent(
    state: HomeUiState,
    onRequestCollect: () -> Unit,
    onAllReport: () -> Unit,
    onItemClick: (Arrest) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            BoxLayout {
                PairingInfo(
                    deviceName = state.pairedDeviceName,
                    isPairedWatch = state.isPairedWatch,
                )
                Spacer(modifier = modifier.height(8.dp))
            }
        }

        item {
            BoxLayout {
                HeartRateInfo(
                    heartRate = state.heartRate,
                    onRequestCollect = onRequestCollect,
                )
                Spacer(modifier = modifier.height(8.dp))
            }
        }

        item {
            ReportInfo(
                emptyReport = state.emptyReport,
                onAllReport = onAllReport,
            )
        }

        items(
            items = state.reportList,
            key = { it.time }
        ) { arrest ->
            BoxLayout(
                padding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 8.dp)
            ) {
                ReportItem(
                    arrestItem = arrest,
                    onItemClick = { onItemClick(arrest) }
                )
            }
        }

        item {
            Spacer(modifier = modifier.height(16.dp))
        }

    }
}

@Composable
private fun HomeAppBar(
    dateStr: String,
    account: Account?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .statusBarsPadding()
    ) {
        Image(
            modifier = modifier
                .size(width = 200.dp, height = 70.dp),
            painter = painterResource(id = com.core.desinsystem.R.drawable.temp_logo_wide),
            contentDescription = null,
        )
        Profile(
            dateStr = dateStr,
            account = account ?: return,
        )
    }

}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}
