package com.core.desinsystem.common

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Clear
import com.core.desinsystem.theme.LogiGray

private const val QUERY_EMPTY_MESSAGE = "검색어를 입력해주세요."
@Composable
fun MySearchTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
    onSearch: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    hint: String = "",
) {
    val context = LocalContext.current
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(45.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .addFocusCleaner(focusManager),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if(query.isNotBlank()) {
                    onSearch()
                    focusManager.clearFocus()
                }
                else {
                    Toast.makeText(context, QUERY_EMPTY_MESSAGE, Toast.LENGTH_SHORT).show()
                }
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
                        text = if (query.isEmpty()) hint else "",
                        color = Color.Gray,
                    )
                    if (query.isNotEmpty()) {
                        Icon(
                            modifier = modifier
                                .padding(horizontal = 8.dp)
                                .noRippleClickable(onClick = onQueryClear),
                            imageVector = Icons.Clear,
                            tint = LogiGray,
                            contentDescription = null
                        )
                    }
                    Icon(
                        modifier = modifier.noRippleClickable {
                            if(query.isNotBlank()) {
                                onSearch()
                                focusManager.clearFocus()
                            }
                            else {
                                Toast.makeText(context, QUERY_EMPTY_MESSAGE, Toast.LENGTH_SHORT).show()
                            }
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
