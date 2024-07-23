package com.feature.arrest.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.ReportItemHeartRate
import com.core.desinsystem.common.ReportItemNormal
import com.core.model.Arrest
import com.feature.arrest.R
import java.time.LocalDateTime

@Composable
fun ArrestContentDetails(
    arrest: Arrest,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = modifier.padding(horizontal = 20.dp),
            text = stringResource(id = R.string.arrest_details_content_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.height(12.dp))
        ArrestDetailsCard(arrest = arrest)
    }
}

@Composable
private fun ArrestDetailsCard(
    arrest: Arrest,
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
            )
            Arrest.ArrestType.HEART_RATE_LOW -> ReportItemHeartRate(
                bpm = arrest.bpm,
                desc = arrest.arrestType.desc,
                date = arrest.date(),
            )
            Arrest.ArrestType.HEART_RATE_HIGH -> ReportItemHeartRate(
                bpm = arrest.bpm,
                desc = arrest.arrestType.desc,
                date = arrest.date(),
            )
        }
        Spacer(modifier = modifier.height(8.dp))
    }
}

@Preview(name = "ArrestContent")
@Composable
private fun PreviewArrestContent() {
    ArrestContentDetails(
        Arrest(
            id = "nal0256",
            time = LocalDateTime.now(),
            lat = 136.12,
            lng = 35.1213,
            bpm = null,
            arrestType = Arrest.ArrestType.NORMAL,
        )
    )
}
