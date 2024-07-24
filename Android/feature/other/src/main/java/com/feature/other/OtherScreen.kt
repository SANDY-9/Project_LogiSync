package com.feature.other

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.core.desinsystem.theme.LogiLightGray

@Composable
fun OtherScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        ArrestDetailsAppBar(onNavigateUp = navController::navigateUp)
        MenuItem(title = stringResource(id = R.string.other_title_finger_print))
        MenuItem(title = stringResource(id = R.string.other_title_face_id))
        MenuItem(title = stringResource(id = R.string.other_title_init))
        MenuItem(title = stringResource(id = R.string.other_title_logout))
    }
}

@Composable
private fun ArrestDetailsAppBar(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .height(56.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onNavigateUp) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
            }
            Text(
                text = stringResource(id = R.string.other_title),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
private fun MenuItem(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .clickable { }
    ) {
        Text(
            modifier = modifier.padding(vertical = 12.dp, horizontal = 20.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge,
        )
        HorizontalDivider(color = LogiLightGray)
    }
}

@Preview(name = "OtherScreen")
@Composable
private fun PreviewOtherScreen() {
    //OtherScreen(rememberNavController())
    MenuItem("로그아웃")
}
