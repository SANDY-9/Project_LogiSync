package com.feature.arrest.details.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
            .padding(horizontal = 16.dp)
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
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
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
        Spacer(modifier = modifier.width(8.dp))
        Column {
            Text(text = "${user.name} (${user.id})")
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = "${user.duty.str()} | ${user.team.name}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.DarkGray
            )
        }
        Spacer(modifier = modifier.weight(1f))
        CallButton(tel = user.tel, context = context)
    }
}
