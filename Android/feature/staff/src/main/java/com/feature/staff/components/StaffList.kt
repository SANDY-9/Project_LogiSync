package com.feature.staff.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.icons.ArrowRange
import com.core.desinsystem.icons.Footprint
import com.core.desinsystem.theme.HeartRed
import com.core.desinsystem.theme.LogiBlue
import com.core.desinsystem.theme.LogiLightGray
import com.core.desinsystem.theme.LogiOrange
import com.core.desinsystem.theme.LogiSemiGray
import com.core.model.Staff

@Composable
internal fun StaffList(
    staffList: List<Staff>,
    state: LazyListState,
    modifier: Modifier = Modifier,
) {
    if(staffList.isEmpty()) {
        EmptyData(modifier = modifier)
    }
    else {
        LazyColumn(
            modifier = modifier,
            state = state,
        ) {
            items(items = staffList) { staff ->
                StaffItem(
                    name = staff.name,
                    bpm = staff.bpm,
                    walk = staff.walk,
                    km = staff.km
                )
                HorizontalDivider(
                    color = LogiSemiGray,
                )
            }
        }
    }
}

@Composable
private fun EmptyData(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            tint = Color.Gray,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "해당하는 데이터가 없습니다.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}


@Composable
private fun StaffItem(
    name: String,
    bpm: Int,
    walk: Int,
    km: Double,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = modifier
                .background(
                    color = LogiLightGray,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(8.dp),
            text = name,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.W600,
                color = Color.DarkGray,
            ),
        )
        Spacer(modifier = modifier.width(16.dp))
        Column(
            modifier = modifier.width(70.dp)
        ) {
            Icon(
                modifier = modifier.size(16.dp),
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null,
                tint = HeartRed,
            )
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "$bpm",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp
                    ),
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    modifier = modifier.padding(bottom = 4.dp),
                    text = "BPM",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )
            }
        }
        Spacer(modifier = modifier.width(16.dp))
        Column(
            modifier = modifier.width(90.dp)
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Footprint,
                contentDescription = null,
                tint = LogiOrange,
            )
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "$walk",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp
                    ),
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    modifier = modifier.padding(bottom = 4.dp),
                    text = "걸음",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )
            }
        }
        Spacer(modifier = modifier.width(16.dp))
        Column {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.ArrowRange,
                contentDescription = null,
                tint = LogiBlue,
            )
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "$km",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 20.sp
                    ),
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    modifier = modifier.padding(bottom = 4.dp),
                    text = "km",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )
            }
        }
    }

}
