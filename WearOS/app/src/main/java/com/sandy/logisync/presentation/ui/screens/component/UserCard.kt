package com.sandy.logisync.presentation.ui.screens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import com.sandy.logisync.R
import com.sandy.logisync.model.Account
import com.sandy.logisync.presentation.ui.theme.BackgroundDeem
import com.sandy.logisync.presentation.ui.theme.DeepRed
import com.sandy.logisync.presentation.ui.theme.LogiBlue

@Composable
fun UserCard(
    account: Account,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = BackgroundDeem,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.Top,
    ) {
        Icon(
            modifier = modifier.padding(top = 6.dp),
            imageVector = Icons.Rounded.AccountCircle,
            tint = LogiBlue,
            contentDescription = null,
        )
        Column(modifier = modifier.weight(1f)) {
            Text(
                modifier = Modifier.weight(1f),
                text = account.name,
                style = MaterialTheme.typography.body1,
                color = Color.White,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = account.id,
                style = MaterialTheme.typography.caption2,
                color = Color.White,
            )
        }
    }
}
