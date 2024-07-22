package com.feature.admin.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.BoxLayout
import com.core.desinsystem.common.CallButton
import com.core.desinsystem.common.EmptyRecordView
import com.core.model.User
import com.core.navigation.Args
import com.core.navigation.Route
import com.feature.admin.details.components.ReportItem
import com.feature.admin.details.components.UserHeartRate
import com.feature.admin.details.components.UserHeartRateReportItem
import com.feature.admin.details.components.UserHeartRateReportTitle
import com.feature.admin.details.components.UserProfile
import com.feature.admin.details.components.UserReport

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(navController.previousBackStackEntry) {
        navController.previousBackStackEntry?.savedStateHandle?.get<User>(Args.USER)?.let { user ->
            viewModel.getUserDetails(user)
        }
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White),
    ) {
        UserDetailsAppBar()
        state.user?.let { user ->
            LazyColumn {

                item {
                    UserProfile(user = user)
                    Spacer(modifier = modifier.height(20.dp))
                }

                item {
                    UserHeartRate(
                        bpm = user.lastBpm,
                        measureDateTime = user.lastBpmDateTime,
                        isWarning = user.isCritical(),
                    )
                }

                item {
                    UserReport(
                        emptyReport = state.lastReportList.isEmpty(),
                        onNavigateToAllReport = {
                            navController.run {
                                currentBackStackEntry?.savedStateHandle?.set(Args.ID, user.id)
                                navigate(Route.Arrest.route)
                            }
                        },
                    )
                    Spacer(modifier = modifier.height(8.dp))
                }

                items(
                    items = state.lastReportList,
                    key = { it.time }
                ) { arrest ->
                    BoxLayout(
                        padding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 8.dp, top = 0.dp)
                    ) {
                        ReportItem(
                            arrestItem = arrest,
                            onItemClick = {
                                navController.run {
                                    currentBackStackEntry?.savedStateHandle?.set(Args.ARREST, arrest)
                                    navigate(Route.ArrestDetails.route)
                                }
                            }
                        )
                    }
                }

                item {
                    UserHeartRateReportTitle(
                        onNavigateToStatistics = {
                            navController.run {
                                currentBackStackEntry?.savedStateHandle?.set(Args.ID, user.id)
                                navigate(Route.Statistics.route)
                            }
                        }
                    )
                }

                items(
                    items = state.lastHeartRateList,
                    key = { it.date }
                ) { heartRate ->
                    UserHeartRateReportItem(heartRate.bpm, heartRate.time())
                }

            }
            if(state.lastReportList.isEmpty()) {
                EmptyRecordView()
            }
        }
    }
}

@Composable
private fun UserDetailsAppBar(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(
            modifier = modifier
                .padding(start = 4.dp),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
        }

        Spacer(modifier = modifier.weight(1f))

        CallButton(
            tel = "010-9198",
            context = context,
        )
        Spacer(modifier = modifier.width(16.dp))
    }
}

@Preview(name = "MemberDetailsScreen")
@Composable
private fun PreviewMemberDetailsScreen() {
    UserDetailsScreen(rememberNavController())
}
