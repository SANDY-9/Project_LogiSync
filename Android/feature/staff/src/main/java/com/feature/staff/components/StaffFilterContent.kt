package com.feature.staff.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Warning
import com.core.desinsystem.theme.LogiOrange
import com.feature.staff.R
import com.feature.staff.model.StaffListUiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun FilterContent(
    walkFilterSelect: Boolean,
    walkDistanceFilterSelect: Boolean,
    heartRateFilterSelect: Boolean,
    onFilterSelect: (StaffListUiState.FilterType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.staff_list_filter_filter_title),
            style = MaterialTheme.typography.titleSmall,
        )
        FlowRow(
            modifier = modifier.fillMaxWidth(),
        ) {
            FilterButton(
                select = walkFilterSelect,
                title = stringResource(id = R.string.staff_list_filter_walk_sort),
                onSelect = { onFilterSelect(StaffListUiState.FilterType.WALK_WARNING) },
            )
            Spacer(modifier = modifier.width(8.dp))
            FilterButton(
                select = walkDistanceFilterSelect,
                title = stringResource(id = R.string.staff_list_filter_walk_distance_sort),
                onSelect = { onFilterSelect(StaffListUiState.FilterType.WALK_DISTANCE_WARNING) },
            )
            Spacer(modifier = modifier.width(8.dp))
            FilterButton(
                select = heartRateFilterSelect,
                title = stringResource(id = R.string.staff_list_filter_heart_rate_sort),
                onSelect = { onFilterSelect(StaffListUiState.FilterType.HEART_RATE_WARNING) },
            )
        }
    }
}

@Composable
private fun FilterButton(
    select: Boolean,
    title: String,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tintColor = if(select) LogiOrange else Color.LightGray
    val textColor = if(select) Color.DarkGray else Color.Gray
    Button(
        modifier = modifier
            .padding(vertical = 4.dp)
            .defaultMinSize(
                minWidth = 1.dp,
                minHeight = 1.dp,
            ).height(30.dp),
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = textColor,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray,
        ),
        onClick = onSelect
    ) {
        Text(text = title)
        Spacer(modifier = modifier.width(6.dp))
        Icon(
            modifier = modifier.size(18.dp),
            imageVector = Icons.Warning,
            contentDescription = null,
            tint = tintColor,
        )
    }
}
