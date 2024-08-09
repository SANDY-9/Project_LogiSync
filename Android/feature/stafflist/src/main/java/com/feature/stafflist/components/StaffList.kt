package com.feature.stafflist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.HeartRed
import com.core.desinsystem.theme.LogiBlue
import com.core.desinsystem.theme.LogiOrange
import com.core.model.Staff

@Composable
internal fun StaffList(
    staffList: List<Staff>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = staffList) { staff ->
            StaffItem(
                name = staff.name,
                bpm = staff.bpm,
                walk = staff.walk,
                km = staff.km
            )
        }
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
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = modifier.width(16.dp))
        Column {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null,
                tint = HeartRed,
            )
            Row {
                Text(
                    text = "$bpm",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "BPM",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                )
            }
        }
        Spacer(modifier = modifier.width(16.dp))
        Column {
            Icon(
                imageVector = Icons.Rounded.Face,
                contentDescription = null,
                tint = LogiOrange,
            )
            Row {
                Text(
                    text = "$walk",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "걸음",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                )
            }
        }
        Spacer(modifier = modifier.width(16.dp))
        Column {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = LogiBlue,
            )
            Row {
                Text(
                    text = "$km",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "km",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                )
            }
        }
    }

}
