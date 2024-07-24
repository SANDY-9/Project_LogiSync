package com.feature.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.core.desinsystem.common.ReportItemHeartRate
import com.core.desinsystem.common.ReportItemNormal
import com.core.model.Arrest

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
