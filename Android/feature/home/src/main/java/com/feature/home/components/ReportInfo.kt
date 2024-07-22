package com.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.LogiCard
import com.core.desinsystem.common.noRippleClickable
import com.feature.home.R

@Composable
internal fun ReportInfo(
    emptyReport: Boolean,
    onAllReport: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
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
            text = stringResource(id = R.string.home_report_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            modifier = modifier.noRippleClickable(onClick = onAllReport),
            text = stringResource(id = R.string.home_report_all),
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
            text = stringResource(id = R.string.home_report_empty),
        )
    }
}

@Preview(name = "ReportInfo")
@Composable
private fun PreviewReportInfo() {
    ReportInfo(true, {})
}
