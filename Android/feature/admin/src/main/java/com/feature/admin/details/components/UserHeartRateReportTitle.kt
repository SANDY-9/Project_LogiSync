package com.feature.admin.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.minDate
import com.core.utils.DateUtil
import com.feature.admin.R
import java.time.LocalDate

private val today = LocalDate.now()
@Composable
internal fun UserHeartRateReportTitle(
    date: LocalDate = LocalDate.now(),
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Text(text = stringResource(id = R.string.details_heart_rate_title))
        Spacer(modifier = modifier.weight(1f))
        IconButton(
            onClick = {},
            enabled = !date.isEqual(minDate)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null
            )
        }
        Text(
            text = DateUtil.convertFullDate(date),
        )
        IconButton(
            onClick = {},
            enabled = !date.isEqual(today)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}
