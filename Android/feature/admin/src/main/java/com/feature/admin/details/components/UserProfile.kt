package com.feature.admin.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.common.BasicOutlinedButton
import com.core.desinsystem.common.CallButton
import com.core.desinsystem.icons.Flag
import com.core.desinsystem.theme.DarkRed
import com.core.desinsystem.theme.HeartRed
import com.core.desinsystem.theme.LogiDarkGray
import com.core.desinsystem.theme.LogiGray
import com.core.desinsystem.theme.LogiOrange
import com.core.desinsystem.theme.TransparentBlack
import com.core.model.User
import com.feature.admin.R
import java.time.LocalDateTime

@Composable
internal fun UserProfile(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = modifier
                    .size(width = 42.dp, height = 42.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = TransparentBlack,
                    ),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = com.core.desinsystem.R.drawable.and),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        modifier = modifier.padding(bottom = 1.dp),
                        text = "(${user.id})",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Spacer(modifier = modifier.height(2.dp))
                Text(
                    text = "${user.team.name}  |  ${user.duty.str()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Spacer(modifier = modifier.weight(1f))
        }
        CriticalPoint(
            minCriticalPoint = user.minCriticalPoint ?: return,
            maxCriticalPoint = user.maxCriticalPoint ?: return
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
        modifier = modifier.padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(22.dp),
            imageVector = Icons.Flag,
            tint = DarkRed,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "최소 $minCriticalPoint - 최대 $maxCriticalPoint",
            color = LogiDarkGray,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                letterSpacing = (-0.5).sp
            )
        )
        Spacer(modifier = modifier.weight(1f))
        BasicOutlinedButton(
            modifier = modifier,
            title = stringResource(id = R.string.details_critical_point_settings),
            onClick = {},
        )
    }
}


@Preview(name = "UserProfile")
@Composable
private fun PreviewUserProfile() {
    UserProfile(
        user = User(
            id = "nal0256",
            name = "홍길동",
            tel = "010-1234-5678",
            duty = User.Duty.NORMAL,
            team = User.Team.물류1팀,
            lastBpm = 88,
            lastBpmDateTime = LocalDateTime.now(),
            minCriticalPoint = 20,
            maxCriticalPoint = 130,
        )
    )
}
