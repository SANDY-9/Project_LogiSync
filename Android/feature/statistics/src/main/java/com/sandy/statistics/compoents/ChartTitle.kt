package com.sandy.statistics.compoents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.minDate
import com.core.utils.DateUtil
import java.time.LocalDate

private val today = LocalDate.now()
@Composable
internal fun DailyChartTitle(
    date: LocalDate,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(30.dp).padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = modifier.size(30.dp),
            onClick = onPrevClick,
            enabled = !date.isEqual(minDate),
            colors = IconButtonColors(
                contentColor = Color.DarkGray,
                disabledContentColor = Color.LightGray,
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null,
            )
        }
        Text(
            modifier = modifier.padding(horizontal = 4.dp),
            text = DateUtil.convertFullDate(date),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray,
        )
        IconButton(
            modifier = modifier.size(30.dp),
            onClick = onNextClick,
            enabled = !date.isEqual(today),
            colors = IconButtonColors(
                contentColor = Color.DarkGray,
                disabledContentColor = Color.LightGray,
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
            )
        }
    }
}

@Composable
internal fun PeriodChartTitle(
    periodTitle: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = periodTitle,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray,
        )
    }
}
