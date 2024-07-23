package com.feature.admin.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.theme.LogiDarkGray
import com.core.desinsystem.theme.LogiSemiGray
import com.feature.admin.R

@Composable
fun UserFilter(
    allFilterSelected: Boolean,
    onSelectAllFilter: () -> Unit,
    dangerFilterSelected: Boolean,
    onSelectDangerFilter: () -> Unit,
    onRefreshList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 15.dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FilterButton(
            isSelected = allFilterSelected,
            onSelect = onSelectAllFilter,
            title = stringResource(id = R.string.admin_member_filter_all)
        )
        Spacer(modifier = modifier.width(8.dp))
        FilterButton(
            isSelected = dangerFilterSelected,
            onSelect = onSelectDangerFilter,
            title = stringResource(id = R.string.admin_member_filter_danger)
        )
        Spacer(modifier = modifier.weight(1f))
        Icon(
            modifier = modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable(onClick = onRefreshList)
                .padding(5.dp),
            imageVector = Icons.Rounded.Refresh,
            tint = LogiDarkGray,
            contentDescription = null
        )
    }
}

@Composable
private fun FilterButton(
    isSelected: Boolean,
    onSelect: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
) {
    val containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.White
    val contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.primary
    val strokeColor = if (isSelected) Color.Transparent else LogiSemiGray
    Button(
        modifier = modifier.defaultMinSize(
            minWidth = 1.dp,
            minHeight = 1.dp,
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = strokeColor,
        ),
        onClick = onSelect,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 13.sp,
                fontWeight = if(isSelected) FontWeight.W500 else FontWeight.W400,
                letterSpacing = (-0.3).sp
            ),
        )
    }
}
