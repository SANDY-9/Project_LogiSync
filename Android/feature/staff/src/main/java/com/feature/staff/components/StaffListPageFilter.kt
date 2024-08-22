package com.feature.staff.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Page
import com.core.desinsystem.theme.LogiBlue

@Composable
internal fun StaffListPageFilter(
    pagingFilterVisible: Boolean,
    onPagingFilterVisibleChange: () -> Unit,
    selectPage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .defaultMinSize(
                minWidth = 1.dp,
                minHeight = 1.dp,
            )
            .height(30.dp),
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = Color.Gray,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray,
        ),
        onClick = onPagingFilterVisibleChange,
    ){
        Icon(
            modifier = modifier.size(18.dp),
            imageVector = Icons.Page,
            contentDescription = null,
        )
        AnimatedVisibility(visible = pagingFilterVisible) {
            PageRadioGroup(
                selectPage = selectPage,
                onPageSelected = onPageSelected
            )
        }
        if(!pagingFilterVisible) {
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = selectPage.toString(),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun PageRadioGroup(
    selectPage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pageList = remember {
        listOf(10, 20, 30)
    }
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ){
        PageRadioButton(selectPage = selectPage, page = pageList[0], onPageSelected = onPageSelected)
        Spacer(modifier = modifier.width(8.dp))
        PageRadioButton(selectPage = selectPage, page = pageList[1], onPageSelected = onPageSelected)
        Spacer(modifier = modifier.width(8.dp))
        PageRadioButton(selectPage = selectPage, page = pageList[2], onPageSelected = onPageSelected)
    }
}

@Composable
private fun PageRadioButton(
    selectPage: Int,
    page: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ){
        RadioButton(
            modifier = modifier
                .size(30.dp)
                .scale(.8f),
            selected = (selectPage == page),
            colors = RadioButtonColors(
                selectedColor = LogiBlue,
                unselectedColor = Color.Gray,
                disabledSelectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                disabledUnselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            ),
            onClick = { onPageSelected(page) }
        )
        Text(
            text = page.toString(),
            style = MaterialTheme.typography.bodyMedium,
        )

    }
}
