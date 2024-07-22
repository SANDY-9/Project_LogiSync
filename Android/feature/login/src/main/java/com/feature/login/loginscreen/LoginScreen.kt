package com.feature.login.loginscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.desinsystem.common.addFocusCleaner
import com.core.desinsystem.common.noRippleClickable
import com.core.desinsystem.lottie.LottieProgressBarBlue
import com.core.desinsystem.theme.LogiSyncTheme
import com.core.model.Account
import com.core.navigation.Route
import com.feature.login.R
import com.feature.login.loginscreen.components.IdInputTextField
import com.feature.login.loginscreen.components.PwdInputTextField
import com.feature.login.loginscreen.model.LoginError

@Composable
fun LoginScreen(
    navController: NavController,
    onLogin: (Account) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    val focusManager = LocalFocusManager.current
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(state.account) {
        val account = state.account
        account?.let {
            onLogin(it)
        }
    }

    val context = LocalContext.current
    LaunchedEffect(state.error) {
        val error = state.error
        if(error != LoginError.NONE) {
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(state.isLoading) {
        if(state.isLoading) {
            focusManager.clearFocus()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = com.core.desinsystem.R.drawable.bg_white),
                contentScale = ContentScale.FillBounds,
            )
            .addFocusCleaner(focusManager),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = modifier.fillMaxHeight(0.3f))

        Image(
            painter = painterResource(id = com.core.desinsystem.R.drawable.temp_logo),
            contentDescription = null
        )

        Spacer(modifier = modifier.height(50.dp))

        IdInputTextField(
            id = state.id,
            onInputChange = viewModel::inputId,
            onClear = viewModel::clearInputId,
            focusManager = focusManager,
        )

        Spacer(modifier = modifier.height(8.dp))

        PwdInputTextField(
            pwd = state.pwd,
            onInputChange = viewModel::inputPwd,
            pwdVisible = state.pwdVisible,
            onVisibleChange = viewModel::visiblePwd,
            focusManager = focusManager
        )

        Spacer(modifier = modifier.height(60.dp))

        Button(
            modifier = modifier.width(280.dp),
            onClick = {
                if(state.id.isNotBlank() && state.pwd.isNotBlank()) {
                    viewModel.requestLogin()
                }
                else {
                    Toast.makeText(context, EMPTY_ID_OR_PWD_MESSAGE, Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = stringResource(id = R.string.login_title_login))
        }

        Spacer(modifier = modifier.height(16.dp))

        Row {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .noRippleClickable(
                        onClick = viewModel::requestBioLogin
                    ),
                text = stringResource(id = R.string.login_title_bio),
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                textAlign = TextAlign.End
            )

            Text(
                modifier = modifier.padding(horizontal = 16.dp),
                text = "|",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray,
            )

            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .noRippleClickable {
                        navController.navigate(route = Route.Signup.route)
                    },
                text = stringResource(id = R.string.login_title_signup),
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                textAlign = TextAlign.Start
            )

        }


        if(state.isLoading) {
            LottieProgressBarBlue(modifier = modifier.padding(top = 32.dp))
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

private const val EMPTY_ID_OR_PWD_MESSAGE = "아이디, 비밀번호를 입력해주세요."
