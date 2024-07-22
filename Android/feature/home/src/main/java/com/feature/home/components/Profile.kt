package com.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.TransparentBlack
import com.core.model.Account

@Composable
internal fun Profile(
    dateStr: String,
    account: Account,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = dateStr,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = modifier.height(12.dp))
        Row(
            modifier = modifier.height(60.dp),
        ) {
            Image(
                modifier = modifier
                    .size(width = 60.dp, height = 60.dp)
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
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(bottom = 4.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "${account.name}님",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        text = "(${account.id})",
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                Spacer(modifier = modifier.height(2.dp))
                Text(
                    text = "${account.team.name}   |   ${account.duty.str()}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
