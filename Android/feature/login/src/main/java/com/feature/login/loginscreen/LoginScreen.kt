package com.feature.login.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.feature.login.R
import com.sandy.designsystem.common.BasicTextButton
import com.sandy.designsystem.common.IDTextField
import com.sandy.designsystem.common.PWTextField
import com.sandy.designsystem.theme.LogiSyncTheme

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    //viewModel: LoginViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.weight(1f))
        Text(
            modifier = modifier,
            text = "LogiSync",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = modifier.weight(1f))
        IDTextField(
            modifier = modifier.fillMaxWidth(),
            placeHolder = stringResource(id = R.string.login_title_id),
            onValueChange = {},
        )
        PWTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeHolder = stringResource(id = R.string.login_title_pwd),
            onValueChange = {},
        )
        BasicTextButton(
            title = stringResource(id = R.string.login_title_signup),
            onClick = { }
        )
        BasicTextButton(
            title = stringResource(id = R.string.login_title_bio),
            onClick = { }
        )
        Spacer(modifier = modifier.weight(1f))
    }
}

@Composable
@Preview(
    name = "LoginScreen",
    showBackground = true,
)
private fun LoginScreenPreview() {
    LogiSyncTheme {
        LoginScreen(
            rememberNavController()
        )
    }
}
