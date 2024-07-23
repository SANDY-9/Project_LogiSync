package com.feature.arrest.details.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.CallButton
import com.core.desinsystem.theme.CallGreen
import com.core.desinsystem.theme.TransparentBlack
import com.core.model.User

@Composable
fun ArrestUserProfile(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ){
        Spacer(modifier = modifier.height(4.dp))
        UserProfile(user = user)
    }
}

@Composable
private fun UserProfile(
    user: User,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = modifier
                .size(width = 40.dp, height = 40.dp)
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
        CallButton(tel = user.tel, context = context)
    }
}
