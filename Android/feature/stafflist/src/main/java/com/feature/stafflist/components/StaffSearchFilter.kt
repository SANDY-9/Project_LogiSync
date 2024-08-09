package com.feature.stafflist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.DarkRed
import com.core.desinsystem.theme.LogiBlue
import com.core.desinsystem.theme.LogiOrange
import com.core.desinsystem.theme.TransparentWhite
import com.feature.stafflist.R

@Composable
internal fun StaffSearchFilter(
    filterExpand: Boolean,
    onFilterExpand: () -> Unit,
    walkSortSelect: Boolean,
    walkDistanceSortSelect: Boolean,
    heartRateSortSelect: Boolean,
    onWalkSortSelect: () -> Unit,
    onWalkDistanceSortSelect: () -> Unit,
    onHeartRateSortSelect: () -> Unit,
    walkFilterSelect: Boolean,
    walkDistanceFilterSelect: Boolean,
    heartRateFilterSelect: Boolean,
    onWalkFilterSelect: () -> Unit,
    onWalkDistanceFilterSelect: () -> Unit,
    onHeartRateFilterSelect: () -> Unit,
    onFilterApply: () -> Unit,
    onFilterInit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        FilterButton(
            filterExpand = filterExpand,
            onFilterExpand = onFilterExpand,
        )
        AnimatedVisibility(visible = filterExpand) {
            if(filterExpand) {
                Column {
                    SortContent(
                        walkSelect = walkSortSelect,
                        walkDistanceSelect = walkDistanceSortSelect,
                        heartRateSelect = heartRateSortSelect,
                        onWalkSortSelect = onWalkSortSelect,
                        onWalkDistanceSortSelect = onWalkDistanceSortSelect,
                        onHeartRateSortSelect = onHeartRateSortSelect,
                    )
                    FilterContent(
                        walkFilterSelect = walkFilterSelect,
                        walkDistanceFilterSelect = walkDistanceFilterSelect,
                        heartRateFilterSelect = heartRateFilterSelect,
                        onWalkFilterSelect = onWalkFilterSelect,
                        onWalkDistanceFilterSelect = onWalkDistanceFilterSelect,
                        onHeartRateFilterSelect = onHeartRateFilterSelect,
                    )
                    FilterSettings(
                        onFilterApply = onFilterApply,
                        onFilterInit = onFilterInit,
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterButton(
    filterExpand: Boolean,
    onFilterExpand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val buttonColor = ButtonColors(
        containerColor = if(filterExpand) LogiBlue else DarkRed,
        contentColor = if(filterExpand) TransparentWhite else Color.White,
        disabledContentColor = Color.Gray,
        disabledContainerColor = Color.LightGray,
    )
    if(filterExpand) {
        Button(
            modifier = modifier.defaultMinSize(
                    minWidth = 1.dp,
                    minHeight = 1.dp,
                ),
            contentPadding = PaddingValues(vertical = 3.dp, horizontal = 16.dp),
            colors = buttonColor,
            onClick = onFilterExpand,
        ) {
            Text(
                text = stringResource(id = R.string.staff_list_filter_select_title)
            )
        }
    }
    else {
        Button(
            modifier = modifier.defaultMinSize(
                minWidth = 1.dp,
                minHeight = 1.dp,
            ),
            contentPadding = PaddingValues(vertical = 3.dp, horizontal = 16.dp),
            colors = buttonColor,
            onClick = onFilterExpand,
        ) {
            Text(
                text = stringResource(id = R.string.staff_list_filter_title)
            )
        }
    }
}

@Composable
private fun SortContent(
    walkSelect: Boolean,
    walkDistanceSelect: Boolean,
    heartRateSelect: Boolean,
    onWalkSortSelect: () -> Unit,
    onWalkDistanceSortSelect: () -> Unit,
    onHeartRateSortSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.staff_list_filter_sort_title_name)
        )
        Row {
            SortButton(
                select = walkSelect,
                title = stringResource(id = R.string.staff_list_filter_walk),
                onSelect = onWalkSortSelect,
            )
            Spacer(modifier = modifier.width(8.dp))
            SortButton(
                select = walkDistanceSelect,
                title = stringResource(id = R.string.staff_list_filter_walk_distance),
                onSelect = onWalkDistanceSortSelect,
            )
            Spacer(modifier = modifier.width(8.dp))
            SortButton(
                select = heartRateSelect,
                title = stringResource(id = R.string.staff_list_filter_heart_rate),
                onSelect = onHeartRateSortSelect,
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
) {
    val tintColor = if(select) LogiBlue else Color.LightGray
    Button(
        modifier = modifier
            .defaultMinSize(
                minWidth = 1.dp,
                minHeight = 1.dp,
            )
            .width(100.dp),
        contentPadding = PaddingValues(vertical = 3.dp, horizontal = 16.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = Color.Gray,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray,
        ),
        onClick = onSelect
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = title)
            Spacer(modifier = modifier.weight(1f))
            Icon(
                imageVector = Icons.Rounded.Done,
                contentDescription = null,
                tint = tintColor,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterContent(
    walkFilterSelect: Boolean,
    walkDistanceFilterSelect: Boolean,
    heartRateFilterSelect: Boolean,
    onWalkFilterSelect: () -> Unit,
    onWalkDistanceFilterSelect: () -> Unit,
    onHeartRateFilterSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.staff_list_filter_filter_title)
        )
        FlowRow(
            modifier = modifier.fillMaxWidth(),
        ) {
            FilterButton(
                select = walkFilterSelect,
                title = stringResource(id = R.string.staff_list_filter_walk_sort),
                onSelect = onWalkFilterSelect,
            )
            Spacer(modifier = modifier.width(8.dp))
            FilterButton(
                select = walkDistanceFilterSelect,
                title = stringResource(id = R.string.staff_list_filter_walk_distance_sort),
                onSelect = onWalkDistanceFilterSelect,
            )
            Spacer(modifier = modifier.width(8.dp))
            FilterButton(
                select = heartRateFilterSelect,
                title = stringResource(id = R.string.staff_list_filter_heart_rate_sort),
                onSelect = onHeartRateFilterSelect,
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
    Button(
        modifier = modifier
            .defaultMinSize(
                minWidth = 1.dp,
                minHeight = 1.dp,
            )
            .padding(vertical = 4.dp)
            .width(150.dp).height(30.dp),
        contentPadding = PaddingValues(vertical = 3.dp, horizontal = 16.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = Color.Gray,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray,
        ),
        onClick = onSelect
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = title)
            Spacer(modifier = modifier.weight(1f))
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null,
                tint = tintColor,
            )
        }
    }
}

@Composable
private fun FilterSettings(
    onFilterApply: () -> Unit,
    onFilterInit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            modifier = modifier.defaultMinSize(
                minWidth = 1.dp,
                minHeight = 1.dp,
            ),
            contentPadding = PaddingValues(vertical = 3.dp, horizontal = 16.dp),
            onClick = onFilterApply,
        ) {
            Text(text = stringResource(id = R.string.staff_list_filter_sort_apply))
        }
        Spacer(modifier = modifier.width(8.dp))
        Button(
            modifier = modifier.defaultMinSize(
                minWidth = 1.dp,
                minHeight = 1.dp,
            ),
            contentPadding = PaddingValues(vertical = 3.dp, horizontal = 16.dp),
            onClick = onFilterInit,
        ) {
            Text(text = stringResource(id = R.string.staff_list_filter_sort_init))
        }
    }
}
