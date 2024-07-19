package com.feature.login.loginscreen.components

import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.MyPwdTextField
import com.core.desinsystem.common.addFocusCleaner
import com.feature.login.R

@Composable
fun PwdInputTextField(
    pwd: String,
    onInputChange: (String) -> Unit,
    pwdVisible: Boolean,
    onVisibleChange: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    MyPwdTextField(
        modifier = modifier.width(280.dp).addFocusCleaner(focusManager),
        value = pwd,
        onValueChange = onInputChange,
        inputVisible = pwdVisible,
        onInputVisibleChange = onVisibleChange,
        focusManager = focusManager,
        label = {
            Text(text = stringResource(id = R.string.login_title_pwd))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
        },
        keyboardActions = {
            focusManager.clearFocus()
        }
    )
}
