package com.feature.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.desinsystem.common.ReportItemHeartRate
import com.core.desinsystem.common.ReportItemNormal
import com.core.model.Arrest
import java.time.LocalDateTime

@Composable
fun ReportItem(
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
@Preview(name = "ReportItem")
@Composable
private fun PreviewReportItem() {
    ReportItem(
        Arrest(
            id = "test1234",
            time = LocalDateTime.now(),
            lat = 36.12124,
            lng = 123.123,
            bpm = 100,
            arrestType = Arrest.ArrestType.NORMAL,
        ),
        {}
    )
}
