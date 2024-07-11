package com.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.BasicOutlinedButton
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
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = modifier.height(12.dp))
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = modifier.height(70.dp),
            ) {
                Image(
                    modifier = modifier
                        .size(width = 60.dp, height = 70.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = com.core.desinsystem.R.drawable.and),
                    contentDescription = null
                )
                Text(
                    modifier = modifier
                        .align(Alignment.Bottom)
                        .padding(horizontal = 8.dp),
                    text = account.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    modifier = modifier
                        .align(Alignment.Bottom),
                    text = "${account.duty.str()}  |  TES물류기술연구소",
                    style = MaterialTheme.typography.titleSmall
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
    modifier: Modifier = Modifier,
) {
    BasicOutlinedButton(
        modifier = modifier,
        title = stringResource(id = R.string.home_profile_fix),
        onClick = { /*TODO*/ }
    )
}

@Preview(name = "Profile")
@Composable
private fun PreviewProfile() {
    Profile("2024년 12월 02일 수요일", Account())
}
