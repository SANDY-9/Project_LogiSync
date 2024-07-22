package com.feature.arrest.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feature.arrest.R

@Composable
fun ArrestLocationDetails(
    lat: Double,
    lng: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ){
        Text(
            text = stringResource(id = R.string.arrest_details_location_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.height(12.dp))
        ArrestLocationMap()
    }
}

@Composable
private fun ArrestLocationMap(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .widthIn(max = 500.dp)
            .background(color = Color.Magenta)
    )
}
@Preview(name = "ArrestLocation")
@Composable
private fun PreviewArrestLocation() {
    ArrestLocationDetails(36.213, 132.31234)
}
