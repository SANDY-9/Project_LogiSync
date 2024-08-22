package com.feature.staff.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Sort
import com.core.desinsystem.theme.LogiBlue
import com.feature.staff.R
import com.feature.staff.model.StaffListUiState

@Composable
internal fun SortContent(
    walkSortSelect: Boolean,
    walkDistanceSortSelect: Boolean,
    heartRateSortSelect: Boolean,
    onFilterSelect: (StaffListUiState.FilterType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.staff_list_filter_sort_title_name),
            style = MaterialTheme.typography.titleSmall,
        )
        Row {
            SortButton(
                select = walkSortSelect,
                title = stringResource(id = R.string.staff_list_filter_walk),
                onSelect = { onFilterSelect(StaffListUiState.FilterType.WALK)},
            )
            Spacer(modifier = modifier.width(8.dp))
            SortButton(
                select = walkDistanceSortSelect,
                title = stringResource(id = R.string.staff_list_filter_walk_distance),
                onSelect = { onFilterSelect(StaffListUiState.FilterType.WALK_DISTANCE)},
                descend = false,
            )
            Spacer(modifier = modifier.width(8.dp))
            SortButton(
                select = heartRateSortSelect,
                title = stringResource(id = R.string.staff_list_filter_heart_rate),
                onSelect = { onFilterSelect(StaffListUiState.FilterType.HEART_RATE)},
            )
        }
    }
}

@Composable
private fun SortButton(
    select: Boolean,
    title: String,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    descend: Boolean = true,
) {
    val selectColor = if(select) LogiBlue else Color.LightGray
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
        Text(text = title,)
        Spacer(modifier = modifier.width(6.dp))
        Icon(
            modifier = modifier.size(20.dp).then(
                if(!descend) modifier.rotate(180f)
                else modifier
            ),
            imageVector = Icons.Sort,
            tint = selectColor,
            contentDescription = null,
        )
    }
}
