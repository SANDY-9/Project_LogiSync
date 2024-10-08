package com.feature.arrest.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.NetworkError
import com.core.model.Arrest
import com.core.navigation.Args
import com.feature.arrest.R
import com.feature.arrest.details.components.ArrestContentDetails
import com.feature.arrest.details.components.ArrestLocationDetails
import com.feature.arrest.details.components.ArrestUserProfile
import com.feature.arrest.details.model.ArrestDetailsUiState

@Composable
fun ArrestDetailsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ArrestDetailsViewModel = hiltViewModel()
) {
     LaunchedEffect(navController.previousBackStackEntry) {
        navController.previousBackStackEntry?.savedStateHandle?.get<Arrest>(Args.ARREST)?.let { arrest ->
            viewModel.getArrestDetails(arrest)
        }
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        ArrestDetailsAppBar(onNavigateUp = navController::navigateUp )
        if(state.error != null) NetworkError(modifier = modifier.fillMaxSize())
        ArrestContent(
            state = state,
            isMapReady = state.mapReady,
            onMapLoaded = viewModel::readyMap,
        )
    }
}

@Composable
private fun ArrestDetailsAppBar(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .height(56.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onNavigateUp) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
            }
            Text(
                text = stringResource(id = R.string.arrest_details_title),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
private fun ArrestContent(
    state: ArrestDetailsUiState,
    isMapReady: Boolean,
    onMapLoaded: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ArrestUserProfile(user = state.user)
        Spacer(modifier = modifier.height(24.dp))
        ArrestContentDetails(arrest = state.arrest ?: return)
        Spacer(modifier = modifier.height(24.dp))
        ArrestLocationDetails(
            arrestLocation = state.arrestLocation ?: return,
            isMapReady = isMapReady,
            onMapLoaded = onMapLoaded,
        )
        Spacer(modifier = modifier.height(30.dp))
    }
}


@Preview(name = "ArrestDetailsScreen")
@Composable
private fun PreviewArrestDetailsScreen() {
    ArrestDetailsScreen(rememberNavController())
}
