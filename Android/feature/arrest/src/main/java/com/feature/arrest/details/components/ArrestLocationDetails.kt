package com.feature.arrest.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.lottie.LottieProgressBarBlue
import com.core.desinsystem.theme.LogiSemiGray
import com.feature.arrest.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun ArrestLocationDetails(
    arrestLocation: LatLng,
    isMapReady: Boolean,
    onMapLoaded: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ){
        Text(
            text = stringResource(id = R.string.arrest_details_location_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.height(12.dp))
        ArrestLocationMap(
            arrestLocation = arrestLocation,
            isMapReady = isMapReady,
            onMapLoaded = onMapLoaded,
        )
    }
}

@Composable
private fun ArrestLocationMap(
    arrestLocation: LatLng,
    isMapReady: Boolean,
    onMapLoaded: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(arrestLocation, 17f)
    }
    val markerState = rememberMarkerState(position = arrestLocation)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .widthIn(max = 500.dp)
            .clip(
                RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = LogiSemiGray,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        GoogleMap(
            modifier = modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = onMapLoaded,
        ) {
            Marker(
                state = markerState,
                anchor = Offset(0.5f,0.5f),
                title = "${arrestLocation.latitude}, ${arrestLocation.longitude}",
                snippet = stringResource(id = R.string.arrest_details_location_arrest),
            )
            LaunchedEffect(isMapReady) {
                if(!isMapReady) markerState.showInfoWindow()
            }
        }
        if(!isMapReady) {
            LottieProgressBarBlue(modifier = modifier.fillMaxSize())
        }
    }
}

@Preview(name = "ArrestLocation")
@Composable
private fun PreviewArrestLocation() {
    ArrestLocationDetails(LatLng(36.213, 132.31234), true, {})
}
