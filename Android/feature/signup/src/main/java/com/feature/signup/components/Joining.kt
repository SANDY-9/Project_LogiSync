package com.feature.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.feature.signup.R
import com.feature.signup.model.JoiningState

// 가입
@Composable
internal fun Joining(
    joining: JoiningState,
    onIdInputChange: (String) -> Unit,
    onIdCheck: () -> Unit,
    onPwdInputChange: (String) -> Unit,
    onPwdCheckInputChange: (String) -> Unit,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {

            Text(
                text = stringResource(id = R.string.signup_join_id),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = modifier.height(12.dp))

            IdInput(
                id = joining.id,
                onInputChange = onIdInputChange,
                isIdError = joining.idError,
                onIdCheckClick = onIdCheck,
                modifier = Modifier,
            )

            Spacer(modifier = modifier.height(32.dp))

            Text(
                text = stringResource(id = R.string.signup_join_pwd),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = modifier.height(12.dp))

            PwdInput(
                pwd = joining.pwd,
                onInputChange = onPwdInputChange,
                pwdCheck = joining.pwdCheck,
                onCheckInputChange = onPwdCheckInputChange,
                isPwdError = joining.pwdError,
                isPwdCheckError = joining.pwdCheckError,
                modifier = Modifier,
            )

        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onComplete
        ) {
            Text(
                text = stringResource(id = R.string.signup_next_step3)
            )
        }
    }
}

@Composable
private fun IdInput(
    id: String,
    onInputChange: (String) -> Unit,
    isIdError: Boolean,
    onIdCheckClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {

        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = id,
            onValueChange = onInputChange,
            placeholder = {
                Text(text = stringResource(id = R.string.signup_join_id_placeholder))
            },
            isError = isIdError,
            supportingText = {
                Text(text = stringResource(id = R.string.signup_join_id_desc))
            },
            singleLine = true,
        )

        Button(
            modifier = modifier.fillMaxHeight(),
            onClick = onIdCheckClick,
        ) {
            Text(text = stringResource(id = R.string.signup_join_id_check))
        }
    }
}

@Composable
private fun PwdInput(
    pwd: String,
    onInputChange: (String) -> Unit,
    pwdCheck: String,
    onCheckInputChange: (String) -> Unit,
    isPwdError: Boolean,
    isPwdCheckError: Boolean,
    modifier: Modifier = Modifier,
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = pwd,
        onValueChange = onInputChange,
        placeholder = {
            Text(text = stringResource(id = R.string.signup_join_pwd_placeholder))
        },
        supportingText = {
            Text(text = stringResource(id = R.string.signup_join_pwd_desc))
        },
        isError = isPwdError,
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )

    Spacer(modifier = modifier.height(8.dp))

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = pwdCheck,
        onValueChange = onCheckInputChange,
        label = {
            Text(text = stringResource(id = R.string.signup_join_pwd_check))
        },
        isError = isPwdCheckError,
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}
