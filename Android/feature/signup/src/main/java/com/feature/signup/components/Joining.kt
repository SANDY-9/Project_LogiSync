package com.feature.signup.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.MyTextField
import com.core.desinsystem.common.addFocusCleaner
import com.core.desinsystem.icons.Check
import com.core.desinsystem.icons.Clear
import com.feature.signup.R
import com.feature.signup.model.InputType
import com.feature.signup.model.JoiningState


// 가입
@Composable
internal fun Joining(
    joining: JoiningState,
    onInputChange: (String, InputType) -> Unit,
    onInputClear: (InputType) -> Unit,
    onIdCheck: () -> Unit,
    onSignupComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .addFocusCleaner(focusManager)
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
                isValidId = joining.isValidId,
                existId = joining.existedId,
                onIdInputChange = {
                    onInputChange(it, InputType.ID)
                },
                onIdInputClear = {
                    onInputClear(InputType.ID)
                },
                onIdCheckClick = {
                    focusManager.clearFocus()
                    onIdCheck()
                },
                focusManager = focusManager,
            )

            Spacer(modifier = modifier.height(32.dp))

            Text(
                text = stringResource(id = R.string.signup_join_pwd),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = modifier.height(12.dp))

            /*PwdInput(
                pwd = joining.pwd,
                onInputChange = onPwdInputChange,
                pwdCheck = joining.pwdCheck,
                onCheckInputChange = onPwdCheckInputChange,
                isPwdError = joining.pwdError,
                isPwdCheckError = joining.pwdCheckError,
                modifier = Modifier,
            )*/
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = joining.isSingUpEnabled,
            onClick = onSignupComplete,
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
    isValidId: Boolean,
    existId: Boolean?,
    onIdInputChange: (String) -> Unit,
    onIdInputClear: () -> Unit,
    onIdCheckClick: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    val borderColor = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = when(existId) {
            null -> MaterialTheme.colorScheme.primary
            true -> MaterialTheme.colorScheme.error
            false -> Color.Green
        }
    )
    Row(
        modifier = modifier,
    ) {
        MyTextField(
            modifier = modifier.weight(1f),
            value = id,
            onValueChange = onIdInputChange,
            onValueClear = onIdInputClear,
            focusManager = focusManager,
            placeholder = {
                Text(text = stringResource(id = R.string.signup_join_id_placeholder))
            },
            supportingText = {
                existId?.let { exist ->
                    if(exist) {
                        ExistId()
                    }
                    else {
                        NotExistId()
                    }
                } ?:
                Text(text = stringResource(id = R.string.signup_join_id_desc))
            },
            isError = id.isNotBlank() && !isValidId,
            colors = borderColor
        )
        Spacer(modifier = modifier.width(16.dp))
        Button(
            modifier = modifier
                .height(54.dp)
                .defaultMinSize(
                    minWidth = 1.dp,
                    minHeight = 1.dp
                ),
            contentPadding = PaddingValues(horizontal = 24.dp),
            shape = RoundedCornerShape(10.dp),
            enabled = isValidId && existId == null,
            onClick = onIdCheckClick,
        ) {
            Text(
                text = stringResource(id = R.string.signup_join_id_check),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun ExistId(
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
            text = stringResource(id = R.string.signup_join_id_check_fail),
            color = MaterialTheme.colorScheme.error,
        )
    }
}

@Composable
private fun NotExistId(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = modifier.size(18.dp),
            imageVector = Icons.Check, contentDescription = null,
            tint = Color.Green,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.signup_join_id_check_ok),
            color = Color.Green,
        )
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
@Preview
@Composable
private fun Preview() {
    var id by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    IdInput(
        id = id,
        isValidId = true,
        existId = true,
        onIdInputChange = {id = it},
        onIdInputClear = {id = ""},
        onIdCheckClick = {},
        focusManager = focusManager,
    )
}
