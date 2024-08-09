package com.feature.stafflist.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.stringResource
import com.core.desinsystem.common.MySearchTextField
import com.feature.stafflist.R

@Composable
internal fun StaffSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
    onSearch: () -> Unit,
    focusManager: FocusManager,
) {
    MySearchTextField(
        query = query,
        onQueryChange = onQueryChange,
        onQueryClear = onQueryClear,
        onSearch = onSearch,
        focusManager = focusManager,
        hint = stringResource(id = R.string.staff_list_search_hint)
    )
}
