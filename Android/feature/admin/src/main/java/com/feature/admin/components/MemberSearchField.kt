package com.feature.admin.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feature.admin.R

@Composable
fun MemberSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                Log.e("확인", "MemberSearchBar: onSearch")
                onSearch()
                keyboardController?.hide()
            }
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier.padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = if (query.isEmpty()) stringResource(id = R.string.admin_search_hint) else "",
                        color = Color.Gray,
                    )
                    if (query.isNotEmpty()) {
                        Icon(
                            modifier = modifier
                                .padding(horizontal = 8.dp)
                                .clickable(onClick = onQueryClear),
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null
                        )
                    }
                    Icon(
                        modifier = modifier.clickable {
                            onSearch()
                            keyboardController?.hide()
                        },
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(end = 64.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            }
        }
    )
}

@Preview(name = "MemberSearchBar")
@Composable
private fun PreviewMemberSearchBar() {
    MemberSearchField("", {}, {}, {})
}
