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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.core.model.Arrest
import com.core.model.HeartRate
import com.core.model.User
import com.core.navigation.Args
import com.core.navigation.Route
import com.feature.admin.details.components.ReportItem
import com.feature.admin.details.components.UserHeartRate
import com.feature.admin.details.components.UserHeartRateReportItem
import com.feature.admin.details.components.UserHeartRateReportTitle
import com.feature.admin.details.components.UserProfile
import com.feature.admin.details.components.UserReport
import com.feature.admin.details.model.UserDetailsUiState

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
            .statusBarsPadding(),
    ) {
        UserDetailsAppBar(
            name = state.user?.name,
            onNavigateUp = { navController.navigateUp() }
        )
        state.user?.let { user ->
            UserDetailsContent(
                user = user,
                lastReportList = state.lastReportList,
                lastHeartRateList = state.lastHeartRateList,
                onNavigateToAllReport = { id ->
                    navController.run {
                        currentBackStackEntry?.savedStateHandle?.set(Args.ID, user.id)
                        navigate(Route.Arrest.route)
                    }
                },
                onArrestItemClick = { arrest ->
                    navController.run {
                        currentBackStackEntry?.savedStateHandle?.set(Args.ARREST, arrest)
                        navigate(Route.ArrestDetails.route)
                    }
                },
                onNavigateToStatistics = { id ->
                    navController.run {
                        currentBackStackEntry?.savedStateHandle?.set(Args.ID, id)
                        navigate(Route.Statistics.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun UserDetailsAppBar(
    onNavigateUp: () -> Unit,
    name: String?,
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
                .padding(start = 6.dp),
            onClick = onNavigateUp,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
        }
        Text(
            text = name ?: "",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.weight(1f))

        CallButton(
            tel = "010-9198",
            context = context,
        )
        Spacer(modifier = modifier.width(20.dp))
    }
}

@Composable
private fun UserDetailsContent(
    user: User,
    lastReportList: List<Arrest>,
    lastHeartRateList: List<HeartRate>,
    onNavigateToAllReport: (String) -> Unit,
    onArrestItemClick: (Arrest) -> Unit,
    onNavigateToStatistics: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn {

        item {
            UserProfile(user = user)
            Spacer(modifier = modifier.height(8.dp))
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
                emptyReport = lastReportList.isEmpty(),
                onNavigateToAllReport = {
                    onNavigateToAllReport(user.id)
                },
            )
            Spacer(modifier = modifier.height(8.dp))
        }

        items(
            items = lastReportList,
            key = { it.time }
        ) { arrest ->
            BoxLayout(
                padding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 8.dp, top = 0.dp)
            ) {
                ReportItem(
                    arrestItem = arrest,
                    onItemClick = {
                        onArrestItemClick(arrest)
                    }
                )
            }
        }

        item {
            UserHeartRateReportTitle(
                onNavigateToStatistics = {
                    onNavigateToStatistics(user.id)
                }
            )
        }

        items(
            items = lastHeartRateList,
            key = { it.date }
        ) { heartRate ->
            val bpm = heartRate.bpm
            val isCritical = isCritical(bpm, user.minCriticalPoint, user.maxCriticalPoint)
            UserHeartRateReportItem(heartRate.bpm, heartRate.time(), isCritical)
        }

    }
    if(lastHeartRateList.isEmpty()) {
        EmptyRecordView()
    }
}

private fun isCritical(bpm: Int?, min: Int?, max: Int?): Boolean {
    return if(min != null && max != null && bpm != null) {
        bpm < min || bpm > max
    } else {
        false
    }
}

@Preview(name = "MemberDetailsScreen")
@Composable
private fun PreviewMemberDetailsScreen() {
    UserDetailsScreen(rememberNavController())
}
