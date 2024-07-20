package com.feature.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.desinsystem.common.ReportItemNormal

@Composable
fun ReportItem(
    modifier: Modifier = Modifier,
) {
    ReportItemNormal()
}
@Preview(name = "ReportItem")
@Composable
private fun PreviewReportItem() {
    ReportItem()
}
