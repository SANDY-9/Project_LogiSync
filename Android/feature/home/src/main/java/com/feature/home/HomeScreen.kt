package com.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.feature.home.components.HeartRateInfo
import com.feature.home.components.PairingInfo
import com.feature.home.components.Profile
import com.feature.home.components.ReportInfo

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeAppBar()
        HomeContent()
    }
}

@Composable
private fun HomeAppBar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            modifier = modifier
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
                contentDescription = null
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = modifier.height(8.dp))
        Profile()
        Spacer(modifier = modifier.height(30.dp))
        PairingInfo()
        Spacer(modifier = modifier.height(30.dp))
        HeartRateInfo()
        Spacer(modifier = modifier.height(30.dp))
        ReportInfo()
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}