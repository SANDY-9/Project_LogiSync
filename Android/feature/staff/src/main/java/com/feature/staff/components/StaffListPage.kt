package com.feature.staff.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.DoubleArrow
import com.core.desinsystem.theme.LogiBlue

@Composable
internal fun StaffListPage(
    currentPage: Int,
    currentPages: List<Int>,
    pageCount: Int,
    isFirstPage: Boolean,
    isEndPage: Boolean,
    onPrevPage: () -> Unit,
    onNextPage: () -> Unit,
    onFirstPage: () -> Unit,
    onEndPage: () -> Unit,
    onPageSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
    ){
        IconButton(
            modifier = modifier.size(30.dp),
            enabled = !isFirstPage,
            colors = IconButtonColors(
                containerColor = Color.White,
                contentColor = LogiBlue,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.LightGray,
            ),
            onClick = onFirstPage,
        ) {
            Icon(
                imageVector = Icons.DoubleArrow,
                contentDescription = null,
            )
        }
        IconButton(
            modifier = modifier.size(30.dp),
            enabled = !isFirstPage,
            colors = IconButtonColors(
                containerColor = Color.White,
                contentColor = LogiBlue,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.LightGray,
            ),
            onClick = onPrevPage
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null,
            )
        }
        PageButton(
            modifier = modifier.weight(1f),
            pageCount = pageCount,
            currentPage = currentPage,
            currentPages = currentPages,
            onPageSelect = onPageSelect,
        )
        IconButton(
            modifier = modifier.size(30.dp),
            enabled = !isEndPage,
            colors = IconButtonColors(
                containerColor = Color.White,
                contentColor = LogiBlue,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.LightGray,
            ),
            onClick = onNextPage
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
            )
        }
        IconButton(
            modifier = modifier.size(30.dp),
            enabled = !isEndPage,
            colors = IconButtonColors(
                containerColor = Color.White,
                contentColor = LogiBlue,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.LightGray,
            ),
            onClick = onEndPage
        ) {
            Icon(
                modifier = modifier.rotate(180f),
                imageVector = Icons.DoubleArrow,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun PageButton(
    pageCount: Int,
    currentPage: Int,
    currentPages: List<Int>,
    onPageSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(5) { page ->
            Spacer(modifier = modifier.weight(1f))
            val isValid = page < pageCount
            val page = if(isValid) currentPages[page] else 0
            val isSelected = page == currentPage
            TextButton(
                modifier = Modifier.size(30.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (isSelected) LogiBlue else Color.White,
                    contentColor = if (isSelected) Color.White else Color.Gray,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                onClick = { onPageSelect(page) },
                enabled = isValid,
            ) {
                Text(text = "$page")
            }
            Spacer(modifier = modifier.weight(1f))
        }
    }
}
