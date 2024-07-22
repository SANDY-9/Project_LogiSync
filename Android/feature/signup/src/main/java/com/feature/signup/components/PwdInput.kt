package com.feature.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.MyPwdTextField
import com.core.desinsystem.icons.Check
import com.core.desinsystem.theme.DarkGreen
import com.feature.signup.R
import com.feature.signup.model.InputType

@Composable
internal fun PwdInput(
    pwd: String,
    pwdCheck: String,
    onInputChange: (String, InputType) -> Unit,
    pwdVisible: Boolean,
    pwdCheckVisible: Boolean,
    onVisibleChange: (InputType) -> Unit,
    isNotValidPwd: Boolean?,
    isNotValidPwdCheck: Boolean?,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        PwdTextField(
            modifier = modifier.fillMaxWidth(),
            pwd = pwd,
            onInputChange = {
                onInputChange(it, InputType.PASSWORD)
            },
            visible = pwdVisible,
            onVisibleChange = {
                onVisibleChange(InputType.PASSWORD)
            },
            isNotValidPwd = isNotValidPwd,
            focusManager = focusManager,
        )
        Spacer(modifier = modifier.height(16.dp))
        PwdCheckTextField(
            modifier = modifier.fillMaxWidth(),
            pwdCheck = pwdCheck,
            onInputChange = {
                onInputChange(it, InputType.PWD_CHECK)
            },
            visible = pwdCheckVisible,
            onVisibleChange = {
                onVisibleChange(InputType.PWD_CHECK)
            },
            isNotValidPwd = isNotValidPwdCheck,
            focusManager = focusManager,
        )
        Spacer(modifier = modifier.height(36.dp))
    }
}

@Composable
private fun PwdTextField(
    pwd: String,
    onInputChange: (String) -> Unit,
    visible: Boolean,
    onVisibleChange: () -> Unit,
    isNotValidPwd: Boolean?,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    MyPwdTextField(
        modifier = modifier,
        value = pwd,
        onValueChange = onInputChange,
        inputVisible = visible,
        onInputVisibleChange = onVisibleChange,
        focusManager = focusManager,
        placeholder = {
            Text(text = stringResource(id = R.string.signup_join_pwd_placeholder))
        },
        supportingText = {
            Text(text = stringResource(id = R.string.signup_join_pwd_desc))
        },
        isError = isNotValidPwd ?: false,
    )
}

@Composable
private fun PwdCheckTextField(
    pwdCheck: String,
    onInputChange: (String) -> Unit,
    visible: Boolean,
    onVisibleChange: () -> Unit,
    isNotValidPwd: Boolean?,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    val borderColor = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = when(isNotValidPwd) {
            null -> MaterialTheme.colorScheme.outline
            true -> MaterialTheme.colorScheme.error
            false -> DarkGreen
        }
    )
    MyPwdTextField(
        modifier = modifier,
        value = pwdCheck,
        onValueChange = onInputChange,
        inputVisible = visible,
        onInputVisibleChange = onVisibleChange,
        focusManager = focusManager,
        placeholder = {
            Text(text = stringResource(id = R.string.signup_join_pwd_check))
        },
        supportingText = {
            isNotValidPwd?.let { notValid ->
                if(notValid) CheckPwdFail() else CheckPwdOk()
            }
        },
        isError = isNotValidPwd ?: false,
        colors = borderColor
    )
}

@Composable
private fun CheckPwdOk(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(18.dp),
            imageVector = Icons.Check, contentDescription = null,
            tint = DarkGreen,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.signup_join_pwd_check_ok),
            color = DarkGreen,
        )
    }
}

@Composable
private fun CheckPwdFail(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(18.dp),
            imageVector = Icons.Rounded.Warning, contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.signup_join_pwd_check_error),
            color = MaterialTheme.colorScheme.error,
        )
    }
}
