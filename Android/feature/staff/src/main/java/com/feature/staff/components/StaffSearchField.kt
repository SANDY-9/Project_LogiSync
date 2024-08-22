package com.feature.staff.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.stringResource
import com.core.desinsystem.common.MySearchTextField
import com.feature.staff.R
import kotlinx.coroutines.launch

@Composable
internal fun StaffSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
    onSearch: () -> Unit,
    lazyListState: LazyListState,
    focusManager: FocusManager,
) {
    val scope = rememberCoroutineScope()
    MySearchTextField(
        query = query,
        onQueryChange = onQueryChange,
        onQueryClear = onQueryClear,
        onSearch = {
            scope.launch {
                lazyListState.animateScrollToItem(0)
            }
            onSearch()
        },
        focusManager = focusManager,
        hint = stringResource(id = R.string.staff_list_search_hint)
    )
}
