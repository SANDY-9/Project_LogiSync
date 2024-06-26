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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.feature.signup.R

// 가입
@Composable
fun Joining(
    onClick: () -> Unit,
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

            Spacer(modifier = modifier.height(8.dp))

            IdInput(
                isIdError = false,
                onCheck = { /*TODO*/ },
                modifier = Modifier,
            )

            Spacer(modifier = modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.signup_join_pwd),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = modifier.height(8.dp)
            )

            PwdInput(
                isPwdError = false,
                isPwdCheckError = false,
                modifier = Modifier,
            )

        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onClick
        ) {
            Text(
                text = stringResource(id = R.string.signup_next_step3)
            )
        }
    }
}

@Composable
private fun IdInput(
    isIdError: Boolean,
    onCheck: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var id by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
    ) {

        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = id,
            onValueChange = { id = it },
            label = {
                Text(text = stringResource(id = R.string.signup_join_id))
            },
            isError = isIdError,
            supportingText = {
                Text(text = stringResource(id = R.string.signup_join_id_desc))
            },
            singleLine = true,
        )

        Button(
            modifier = modifier.fillMaxHeight(),
            onClick = onCheck
        ) {
            Text(text = stringResource(id = R.string.signup_join_id_check))
        }
    }
}

@Composable
private fun PwdInput(
    isPwdError: Boolean,
    isPwdCheckError: Boolean,
    modifier: Modifier = Modifier,
) {

    var pwd by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = pwd,
        onValueChange = { pwd = it },
        label = {
            Text(text = stringResource(id = R.string.signup_join_pwd))
        },
        isError = isPwdError,
        supportingText = {
            Text(text = stringResource(id = R.string.signup_join_pwd_desc))
        },
        singleLine = true,
    )

    Spacer(modifier = modifier.height(8.dp))

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = pwd,
        onValueChange = { pwd = it },
        label = {
            Text(text = stringResource(id = R.string.signup_join_pwd_check))
        },
        visualTransformation = PasswordVisualTransformation(),
        isError = isPwdCheckError,
        singleLine = true,
    )
}

@Composable
@Preview
fun Preview() {
    Joining(onClick = { /*TODO*/ })
}
