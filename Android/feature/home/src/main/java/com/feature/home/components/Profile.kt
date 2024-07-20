package com.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.TransparentBlack
import com.core.model.Account
import com.feature.home.R

@Composable
fun Profile(
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
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                modifier = modifier.padding(start = 74.dp, top = 8.dp),
                text = stringResource(id = R.string.home_profile_hello),
                style = MaterialTheme.typography.labelSmall,
            )
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
                Text(
                    modifier = modifier
                        .align(Alignment.Bottom)
                        .padding(horizontal = 12.dp),
                    text = account.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    modifier = modifier.align(Alignment.Bottom)
                        .padding(bottom = 2.dp),
                    text = "${account.duty.str()}  |  ${account.team.name}",
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            ProfileFixButton(
                modifier = modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
private fun ProfileFixButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 16.dp),
        onClick = onClick,
    ) {
        Text(
            text = stringResource(id = R.string.home_profile_fix)
        )
    }
}
