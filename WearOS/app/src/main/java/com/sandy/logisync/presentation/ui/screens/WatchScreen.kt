package com.sandy.logisync.presentation.ui.screens

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.scrollAway
import com.sandy.logisync.R
import com.sandy.logisync.model.Account
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.presentation.ui.screens.component.HeartRateCard
import com.sandy.logisync.presentation.ui.screens.component.HeartRateCollecting
import com.sandy.logisync.presentation.ui.theme.BackgroundDeem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WatchScreen(
    initialPaired: Boolean,
    heartRate: HeartRate?,
    account: Account?,
    collectLoading: Boolean,
    onCollect: () -> Unit,
    onArrest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (!initialPaired) {
        NotInitialPairedScreen()
    }
    else {
        val listState = rememberScalingLazyListState()
        Scaffold(
            timeText = {
                TimeText(modifier = modifier.scrollAway(listState))
            },
            positionIndicator = {
                PositionIndicator(
                    scalingLazyListState = listState,
                )
            }
        ) {
            if (collectLoading) {
                HeartRateCollecting()
            }
            else {
                WatchScreenContent(
                    listState = listState,
                    account = account,
                    heartRate = heartRate,
                    onCollect = onCollect,
                    onArrest = onArrest,
                )
            }
        }
    }
    var initialCompose by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(1000)
        initialCompose = true
    }
    if(!initialCompose) {
        Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun WatchScreenContent(
    listState: ScalingLazyListState,
    account: Account?,
    heartRate: HeartRate?,
    onCollect: () -> Unit,
    onArrest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = modifier.height(36.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.caption2,
                )
                Spacer(modifier = modifier.height(2.dp))
                Row {
                    Icon(
                        modifier = modifier.size(20.dp),
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null,
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    if(account != null) {
                        Text(
                            text = "${account.name} (${account.id}) ",
                            style = MaterialTheme.typography.caption2,
                        )
                    }
                }
                Spacer(modifier = modifier.height(16.dp))
            }
        }
        item {
            Column {
                HeartRateCard(heartRate = heartRate)
                Spacer(modifier = modifier.height(8.dp))
            }
        }
        item {
            Column {
                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = {
                        onCollect()
                        scope.launch {
                            listState.scrollToItem(0)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BackgroundDeem,
                        contentColor = Color.White,
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.heart_rate_collect),
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(8.dp))
                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = onArrest,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BackgroundDeem,
                        contentColor = Color.White,
                    ),
                ) {
                    Text(
                        text = stringResource(id = R.string.heart_rate_arrest),
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }

    }
}
