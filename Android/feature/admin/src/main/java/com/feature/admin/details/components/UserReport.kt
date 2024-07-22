package com.feature.admin.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.LogiCard
import com.core.desinsystem.common.ReportItemHeartRate
import com.core.desinsystem.common.ReportItemNormal
import com.core.desinsystem.common.noRippleClickable
import com.core.model.Arrest
import com.feature.admin.R

@Composable
internal fun UserReport(
    emptyReport: Boolean,
    onAllReport: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        ReportTitle(onAllReport = onAllReport)
        Spacer(modifier = modifier.size(12.dp))
        if(emptyReport) {
            EmptyReport()
        }
    }
}

@Composable
private fun ReportTitle(
    onAllReport: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(id = R.string.details_arrest_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            modifier = modifier.noRippleClickable(onClick = onAllReport),
            text = stringResource(id = R.string.details_arrest_all),
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray,
        )
    }
}

@Composable
private fun EmptyReport(
    modifier: Modifier = Modifier,
) {
    LogiCard{
        Text(
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.details_arrest_empty),
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
        )
    }
}

@Composable
internal fun ReportItem(
    arrestItem: Arrest,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when(arrestItem.arrestType) {
        Arrest.ArrestType.NORMAL -> ReportItemNormal(
            arrestItem.arrestType.desc,
            arrestItem.date(),
            onItemClick = onItemClick,
        )
        Arrest.ArrestType.HEART_RATE_LOW -> ReportItemHeartRate(
            bpm = arrestItem.bpm,
            arrestItem.arrestType.desc,
            arrestItem.date(),
            onItemClick = onItemClick,
        )
        Arrest.ArrestType.HEART_RATE_HIGH -> ReportItemHeartRate(
            bpm = arrestItem.bpm,
            arrestItem.arrestType.desc,
            arrestItem.date(),
            onItemClick = onItemClick,
        )
    }
}
