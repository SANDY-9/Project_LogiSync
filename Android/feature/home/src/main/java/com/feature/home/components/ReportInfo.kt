package com.feature.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feature.home.R

@Composable
fun ReportInfo(
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.home_report_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.size(12.dp))
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally)
                    .padding(24.dp),
                text = stringResource(id = R.string.home_report_empty),
            )
        }
    }
}

@Preview(name = "ReportInfo")
@Composable
private fun PreviewReportInfo() {
    ReportInfo()
}
