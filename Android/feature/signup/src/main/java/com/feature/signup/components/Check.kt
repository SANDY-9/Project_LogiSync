package com.feature.signup.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.MyTelTextField
import com.core.desinsystem.common.MyTextField
import com.core.desinsystem.common.addFocusCleaner
import com.feature.signup.R
import com.feature.signup.model.CheckState
import com.feature.signup.model.InputType

private const val EXIST_MEMBER_ERROR = "이미 가입되어 있는 회원입니다."
// 회원가입 여부 확인
@Composable
internal fun Check(
    check: CheckState,
    onInputChange: (String, InputType) -> Unit,
    onInputClear: (InputType) -> Unit,
    onSignupCheck: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    LaunchedEffect(check.existedId) {
        if (check.existedId) {
            Toast.makeText(context, EXIST_MEMBER_ERROR, Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val focusManager = LocalFocusManager.current

        Column(
            modifier = modifier
                .weight(1f)
                .addFocusCleaner(focusManager)
        ) {
            Text(
                text = stringResource(id = R.string.signup_check_title),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = modifier.height(30.dp))

            NameTextField(
                input = check.name,
                onInputChange = {
                    onInputChange(it, InputType.NAME)
                },
                onInputClear = {
                    onInputClear(InputType.NAME)
                },
                focusManager = focusManager
            )

            Spacer(modifier = modifier.height(12.dp))

            TelTextField(
                input = check.tel,
                onInputChange = {
                    onInputChange(it, InputType.TEL)
                },
                onInputClear = {
                    onInputClear(InputType.TEL)
                },
                focusManager = focusManager
            )
        }

        Button(
            modifier = modifier.fillMaxWidth(),
            enabled = check.isInputComplete,
            onClick = onSignupCheck,
        ) {
            Text(
                text = stringResource(id = R.string.signup_next_step1)
            )
        }
    }
}
@Composable
private fun NameTextField(
    input: String,
    onInputChange: (String) -> Unit,
    onInputClear: () -> Unit,
    focusManager: FocusManager,
) {
    MyTextField(
        value = input,
        onValueChange = onInputChange,
        onValueClear = onInputClear,
        focusManager = focusManager,
        label = {
            Text(text = stringResource(id = R.string.signup_check_name_label))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.signup_check_name_placeholder))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = null
            )
        },
    )
}
@Composable
private fun TelTextField(
    input: String,
    onInputChange: (String) -> Unit,
    onInputClear: () -> Unit,
    focusManager: FocusManager,
) {
    MyTelTextField(
        value = input,
        onValueChange = onInputChange,
        onValueClear = onInputClear,
        focusManager = focusManager,
        label = {
            Text(text = stringResource(id = R.string.signup_check_tel_label))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.signup_check_tel_placeholder))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Call,
                contentDescription = null
            )
        },
        keyboardActions = {
            focusManager.clearFocus()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
    )
}
@Preview
@Composable
fun CheckPreview() {
    var query: String by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    TelTextField(
        input = query,
        onInputChange = { query = it },
        onInputClear = { query = "" },
        focusManager = focusManager,
    )
}
