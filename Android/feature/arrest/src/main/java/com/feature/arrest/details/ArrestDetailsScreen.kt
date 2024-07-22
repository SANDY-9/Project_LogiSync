package com.feature.arrest.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
            .background(color = Color.White)
    ) {
        ArrestDetailsAppBar(onNavigateUp = { navController.navigateUp() })
        ArrestContent(state)
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
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
private fun ArrestContent(
    state: ArrestDetailsUiState,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ArrestUserProfile(user = state.user ?: return)
        Spacer(modifier = modifier.height(30.dp))
        ArrestContentDetails(arrest = state.arrest ?: return)
        Spacer(modifier = modifier.height(30.dp))
        ArrestLocationDetails(
            lat = state.arrest.lat,
            lng = state.arrest.lng,
        )
        Spacer(modifier = modifier.height(30.dp))
    }
}


@Preview(name = "ArrestDetailsScreen")
@Composable
private fun PreviewArrestDetailsScreen() {
    ArrestDetailsScreen(rememberNavController())
}
