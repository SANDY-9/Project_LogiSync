package com.feature.admin.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Warning
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
import com.core.desinsystem.icons.Flag
import com.core.desinsystem.theme.DarkRed
import com.core.desinsystem.theme.HeartRed
import com.core.desinsystem.theme.LogiDarkGray
import com.core.desinsystem.theme.LogiLightGray
import com.core.desinsystem.theme.LogiSemiGray
import com.core.model.User
import com.core.utils.DateUtil

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserList(
    userList: Map<User.Team, List<User>>,
    onItemClick: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        userList.forEach { (team, users) ->
            stickyHeader {
                TeamStickyHeader(team = team)
            }
            items(
                items = users,
                key = { it.id }
            ) { user ->
                Column {
                    UserItem(
                        user = user,
                        onItemClick = onItemClick,
                    )
                    HorizontalDivider(color = LogiSemiGray)
                }
            }
        }
    }
}

@Composable
private fun TeamStickyHeader(
    team: User.Team,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = LogiLightGray)
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            modifier = modifier.align(Alignment.CenterStart),
            text = team.name,
            style = MaterialTheme.typography.labelSmall
        )

    }
}

@Composable
private fun UserItem(
    user: User,
    onItemClick: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(user) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            UserName(
                name = user.name,
                isCritical = user.isCritical(),
            )
            CriticalPoint(
                modifier = modifier.padding(top = 2.dp),
                minCriticalPoint = user.minCriticalPoint ?: return,
                maxCriticalPoint = user.maxCriticalPoint ?: return,
            )
        }
        Column(
            modifier = modifier.align(Alignment.CenterEnd),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            MeasuredBpm(
                bpm = user.lastBpm ?: return
            )
            Text(
                text = DateUtil.convertDateTime(user.lastBpmDateTime ?: return),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400,
                    letterSpacing = (-0.2).sp
                ),
                color = Color.Gray,
            )
        }
    }
}

@Composable
private fun UserName(
    name: String,
    isCritical: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
        if(isCritical) {
            Spacer(modifier = modifier.width(4.dp))
            Icon(
                modifier = modifier.size(18.dp),
                imageVector = Icons.Rounded.Warning,
                tint = DarkRed,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun CriticalPoint(
    minCriticalPoint: Int,
    maxCriticalPoint: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Flag,
            tint = DarkRed,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "$minCriticalPoint - $maxCriticalPoint",
            color = LogiDarkGray,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                letterSpacing = (-0.2).sp
            )
        )
    }
}

@Composable
private fun MeasuredBpm(
    bpm: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(15.dp),
            imageVector = Icons.Rounded.Favorite,
            tint = HeartRed,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = "$bpm",
            style = MaterialTheme.typography.titleMedium.copy(
                lineHeight = 18.sp
            ),
        )
    }
}
