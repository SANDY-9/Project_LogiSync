package com.feature.stafflist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun StaffListPage(
    selectPage: Int,
    currentPages: List<Int>,
    onPrevPage: () -> Unit,
    onFirstPage: () -> Unit,
    onNextPage: () -> Unit,
    onEndPage: () -> Unit,
    onPageSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ){
        TextButton(
            modifier = modifier.size(25.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = onFirstPage
        ) {
            Text(text = "<<")
        }
        TextButton(
            modifier = modifier.size(25.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = onPrevPage
        ) {
            Text(text = "<")
        }
        PageButton(
            modifier = modifier.weight(1f),
            selectPage = selectPage,
            currentPages = currentPages,
            onPageSelect = onPageSelect,
        )
        TextButton(
            modifier = modifier.size(25.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = onNextPage,
        ) {
            Text(text = ">")
        }
        TextButton(
            modifier = modifier.size(25.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = onEndPage,
        ) {
            Text(text = ">>")
        }
    }
}

@Composable
private fun PageButton(
    selectPage: Int,
    currentPages: List<Int>,
    onPageSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(5) { page ->
            //val isValidPage = page > currentPages.size - 1
            val isSelected = currentPages[page] == selectPage
            TextButton(
                modifier = modifier
                    .width(50.dp)
                    .height(25.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    onPageSelect(page)
                },
              //  enabled = !isValidPage,
            ) {
                Text(
                    text = "${currentPages[page]}",
                    color = Color.Black,
                )
            }
            Spacer(modifier = modifier.weight(1f))
        }
    }
}
