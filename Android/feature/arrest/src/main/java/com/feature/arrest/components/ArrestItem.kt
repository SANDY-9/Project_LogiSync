package com.feature.arrest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.ReportItemHeartRate
import com.core.desinsystem.common.ReportItemNormal
import com.core.model.Arrest

@Composable
internal fun ArrestItem(
    arrest: Arrest,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        when(arrest.arrestType) {
            Arrest.ArrestType.NORMAL -> ReportItemNormal(
                arrest.arrestType.desc,
                arrest.date(),
            )
            Arrest.ArrestType.HEART_RATE_LOW -> ReportItemHeartRate(
                bpm = arrest.bpm,
                arrest.arrestType.desc,
                arrest.date(),
            )
            Arrest.ArrestType.HEART_RATE_HIGH -> ReportItemHeartRate(
                bpm = arrest.bpm,
                arrest.arrestType.desc,
                arrest.date(),
            )
        }
        Spacer(modifier = modifier.height(8.dp))
    }
}
