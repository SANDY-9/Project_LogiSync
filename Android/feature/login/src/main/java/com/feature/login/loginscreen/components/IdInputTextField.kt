package com.feature.login.loginscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.MyTextField
import com.core.desinsystem.common.addFocusCleaner
import com.feature.login.R

@Composable
fun IdInputTextField(
    id: String,
    onInputChange: (String) -> Unit,
    onClear: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    MyTextField(
        modifier = modifier.width(280.dp).addFocusCleaner(focusManager),
        value = id,
        onValueChange = onInputChange,
        onValueClear = onClear,
        focusManager = focusManager,
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Person, contentDescription = null)
        },
        label = {
            Text(text = stringResource(id = R.string.login_title_id))
        },
    )
}
@Preview(name = "IdInputTextField")
@Composable
private fun PreviewIdInputTextField() {
    val focusManager = LocalFocusManager.current
    var id by remember { mutableStateOf("") }
    IdInputTextField(
        id = id,
        onInputChange = { id = it },
        onClear = { id = "" },
        focusManager,
    )
}
