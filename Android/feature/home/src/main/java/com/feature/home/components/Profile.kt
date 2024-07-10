package com.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.BasicOutlinedButton
import com.feature.home.R

@Composable
fun Profile(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "2024년 07월 10일 수요일",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = modifier.height(12.dp))
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
                text = "임선미",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                modifier = modifier
                    .align(Alignment.Bottom),
                text = "사원 | TES 물류연구소",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = modifier.weight(1f))
            ProfileFixButton(
                modifier = modifier.align(Alignment.Top)
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
    Profile()
}
