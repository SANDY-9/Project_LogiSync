import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Filter
import com.core.desinsystem.theme.DarkRed
import com.core.desinsystem.theme.LogiBlue
import com.core.desinsystem.theme.TransparentWhite
import com.feature.staff.R
import com.feature.staff.components.FilterContent
import com.feature.staff.components.SortContent
import com.feature.staff.components.StaffListPageFilter
import com.feature.staff.model.StaffListUiState
import kotlinx.coroutines.launch

@Composable
internal fun StaffSearchFilter(
    filterExpand: Boolean,
    onFilterExpand: () -> Unit,
    walkSortSelect: Boolean,
    walkDistanceSortSelect: Boolean,
    heartRateSortSelect: Boolean,
    walkFilterSelect: Boolean,
    walkDistanceFilterSelect: Boolean,
    heartRateFilterSelect: Boolean,
    onFilterSelect: (StaffListUiState.FilterType) -> Unit,
    onFilterApply: () -> Unit,
    onFilterInit: () -> Unit,
    selectPaging: Int,
    pagingFilterVisible: Boolean,
    onPagingFilterVisibleChange: () -> Unit,
    onPagingSelected: (Int) -> Unit,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            FilterButton(
                filterExpand = filterExpand,
                onFilterExpand = onFilterExpand,
            )
            Spacer(modifier = modifier.weight(1f))
            StaffListPageFilter(
                pagingFilterVisible = pagingFilterVisible,
                onPagingFilterVisibleChange = onPagingFilterVisibleChange,
                selectPage = selectPaging,
                onPageSelected = onPagingSelected,
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        AnimatedVisibility(visible = filterExpand) {
            Column {
                SortContent(
                    walkSortSelect = walkSortSelect,
                    walkDistanceSortSelect = walkDistanceSortSelect,
                    heartRateSortSelect = heartRateSortSelect,
                    onFilterSelect = onFilterSelect,
                )
                Spacer(modifier = modifier.height(8.dp))
                FilterContent(
                    walkFilterSelect = walkFilterSelect,
                    walkDistanceFilterSelect = walkDistanceFilterSelect,
                    heartRateFilterSelect = heartRateFilterSelect,
                    onFilterSelect = onFilterSelect,
                )
                Spacer(modifier = modifier.height(4.dp))
                FilterSettings(
                    onFilterApply = {
                        scope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                        onFilterApply()
                    },
                    onFilterInit = onFilterInit,
                )
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
            modifier = modifier
                .defaultMinSize(
                    minWidth = 1.dp,
                    minHeight = 1.dp,
                )
                .height(30.dp),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
            colors = buttonColor,
            shape = RoundedCornerShape(8.dp),
            onClick = onFilterExpand,
        ) {
            Text(
                text = stringResource(id = R.string.staff_list_filter_select_title)
            )
            Spacer(modifier = modifier.width(6.dp))
            Icon(
                modifier = modifier.size(18.dp),
                imageVector = Icons.Filter,
                contentDescription = null
            )
        }
    }
    else {
        Button(
            modifier = modifier
                .defaultMinSize(
                    minWidth = 1.dp,
                    minHeight = 1.dp,
                )
                .height(30.dp),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
            colors = buttonColor,
            shape = RoundedCornerShape(8.dp),
            onClick = onFilterExpand,
        ) {
            Text(
                text = stringResource(id = R.string.staff_list_filter_title)
            )
            Spacer(modifier = modifier.width(6.dp))
            Icon(
                modifier = modifier
                    .size(18.dp)
                    .rotate(180f),
                imageVector = Icons.Filter,
                contentDescription = null
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
        Spacer(modifier = modifier.weight(1f))
        Button(
            modifier = modifier
                .defaultMinSize(
                    minWidth = 1.dp,
                    minHeight = 1.dp,
                )
                .height(30.dp),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 14.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.DarkGray,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.LightGray,
            ),
            onClick = onFilterInit,
        ) {
            Text(text = stringResource(id = R.string.staff_list_filter_sort_init))
        }
        Spacer(modifier = modifier.width(8.dp))
        Button(
            modifier = modifier
                .defaultMinSize(
                    minWidth = 1.dp,
                    minHeight = 1.dp,
                )
                .height(30.dp),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 14.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = onFilterApply,
        ) {
            Text(text = stringResource(id = R.string.staff_list_filter_sort_apply))
        }
    }
}
