package com.feature.login.loginscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.model.Account
import com.core.navigation.Route
import com.feature.login.R
import com.sandy.designsystem.theme.LogiSyncTheme

@Composable
fun LoginScreen(
    navController: NavController,
    onLogin: (Account) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(state.account) {
        val account = state.account
        account?.let {
            onLogin(it)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            modifier = modifier,
            text = "LogiSync",
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = modifier.height(32.dp))

        OutlinedTextField(
            value = state.id,
            onValueChange = { viewModel.inputId(it) },
            label = {
                Text(text = stringResource(id = R.string.login_title_id))
            },
            singleLine = true,
        )

        OutlinedTextField(
            value = state.pwd,
            onValueChange = { viewModel.inputPwd(it) },
            label = {
                Text(text = stringResource(id = R.string.login_title_pwd))
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
        )

        Spacer(modifier = modifier.height(32.dp))

        Button(
            onClick = { viewModel.requestLogin() }
        ) {
            Text(text = stringResource(id = R.string.login_title_login))
        }
        Button(
            onClick = { viewModel.requestBioLogin() }
        ) {
            Text(text = stringResource(id = R.string.login_title_bio))
        }

        TextButton(
            onClick = {
                navController.navigate(route = Route.Signup.route)
            }
        ) {
            Text(text = stringResource(id = R.string.login_title_signup))
        }

    }
}

@Preview(
    name = "LoginScreen",
    showBackground = true,
)
@Composable
private fun LoginScreenPreview() {
    LogiSyncTheme {
        LoginScreen(
            navController = rememberNavController(),
            {}
        )
    }
}
