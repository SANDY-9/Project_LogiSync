package com.feature.arrest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.ReportItemHeartRate
import com.core.desinsystem.common.ReportItemNormal
import com.core.model.Arrest
import com.feature.arrest.R

@Composable
internal fun ArrestItem(
    arrest: Arrest,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        when(arrest.arrestType) {
            Arrest.ArrestType.NORMAL -> ReportItemNormal(
                desc = arrest.arrestType.desc,
                date = arrest.date(),
                onItemClick = onItemClick,
            )
            Arrest.ArrestType.HEART_RATE_LOW -> ReportItemHeartRate(
                bpm = arrest.bpm,
                desc = arrest.arrestType.desc,
                date = arrest.date(),
                onItemClick = onItemClick,
            )
            Arrest.ArrestType.HEART_RATE_HIGH -> ReportItemHeartRate(
                bpm = arrest.bpm,
                desc = arrest.arrestType.desc,
                date = arrest.date(),
                onItemClick = onItemClick,
            )
        }
        Spacer(modifier = modifier.height(8.dp))
    }
}

@Composable
internal fun EmptyArrestItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            tint = Color.Gray,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.arrest_empty),
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray
        )
    }
}
