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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.model.Member
import com.core.utils.DateUtil
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemberList(
    memberList: Map<Member.Team, List<Member>>,
    onItemClick: (Member) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        memberList.forEach { (team, members) ->
            stickyHeader {
                TeamStickyHeader(team = team)
            }
            items(
                count = members.size
            ) { index ->
                Column {
                    MemberItem(
                        member = members[index],
                        onItemClick = onItemClick,
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun TeamStickyHeader(
    team: Member.Team,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
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
private fun MemberItem(
    member: Member,
    onItemClick: (Member) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(member) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            MemberName(
                name = member.name
            )
            CriticalPoint(
                minCriticalPoint = member.minCriticalPoint,
                maxCriticalPoint = member.maxCriticalPoint
            )
        }
        Column(
            modifier = modifier.align(Alignment.CenterEnd),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            MeasuredBpm(
                bpm = member.lastBpm
            )
            Text(
                text = DateUtil.convertDateTime(member.lastBpmDateTime),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun MemberName(
    name: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = modifier.width(4.dp))
        Icon(
            modifier = modifier.size(20.dp),
            imageVector = Icons.Rounded.Warning,
            contentDescription = null
        )
    }
}

@Composable
private fun CriticalPoint(
    minCriticalPoint: Int,
    maxCriticalPoint: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(15.dp),
            imageVector = Icons.Rounded.Check,
            tint = Color.Gray,
            contentDescription = null
        )
        Spacer(modifier = modifier.width(2.dp))
        Text(
            text = "$minCriticalPoint - $maxCriticalPoint",
            color = Color.Gray,
            style = MaterialTheme.typography.labelSmall
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
            contentDescription = null
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "$bpm",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            modifier = modifier.padding(top = 5.dp),
            text = "bpm",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(name = "MemberList")
@Composable
private fun PreviewMemberList() {
    val memberList = mapOf(
        Member.Team.물류1팀 to List(10) {
            Member(
                id = "",
                name = "홍길동",
                tel = "010-1234-5678",
                duty = Member.Duty.NORMAL,
                team = Member.Team.물류1팀,
                lastBpm = 88,
                lastBpmDateTime = LocalDateTime.now(),
                minCriticalPoint = 20,
                maxCriticalPoint = 130,
            )
        },
        Member.Team.물류2팀 to List(10) {
            Member(
                id = "",
                name = "홍길동",
                tel = "010-1234-5678",
                duty = Member.Duty.NORMAL,
                team = Member.Team.물류1팀,
                lastBpm = 88,
                lastBpmDateTime = LocalDateTime.now(),
                minCriticalPoint = 20,
                maxCriticalPoint = 130,
            )
        },
        Member.Team.TES물류기술연구소 to List(10) {
            Member(
                id = "",
                name = "홍길동",
                tel = "010-1234-5678",
                duty = Member.Duty.NORMAL,
                team = Member.Team.물류1팀,
                lastBpm = 88,
                lastBpmDateTime = LocalDateTime.now(),
                minCriticalPoint = 20,
                maxCriticalPoint = 130,
            )
        }
    )
    MemberList(memberList = memberList, {})
}
