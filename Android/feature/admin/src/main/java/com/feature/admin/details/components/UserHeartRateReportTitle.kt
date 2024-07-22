package com.feature.admin.details.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.minDate
import com.core.desinsystem.common.noRippleClickable
import com.core.utils.DateUtil
import com.feature.admin.R
import java.time.LocalDate

private val today = LocalDate.now()
@Composable
internal fun UserHeartRateReportTitle(
    onNavigateToStatistics: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.details_heart_rate_title),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            modifier = modifier.noRippleClickable(onClick = onNavigateToStatistics),
            text = stringResource(id = R.string.details_heart_rate_all),
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray,
        )
    }
}
