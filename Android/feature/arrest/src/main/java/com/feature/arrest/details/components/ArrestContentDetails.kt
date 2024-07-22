package com.feature.arrest.details.components

import androidx.compose.foundation.layout.Box
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
import com.core.model.Arrest
import com.feature.arrest.R
import com.feature.arrest.components.ArrestItem
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
            modifier = modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.arrest_details_content_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = modifier.height(12.dp))
        ArrestItem(
            arrest = arrest,
            onItemClick = {}
        )

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
