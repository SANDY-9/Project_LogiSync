package com.core.desinsystem.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
private fun TextFieldPlaceHolder(
    str: String,
): @Composable () -> Unit = {
    Text(
        text = str
    )
}

@Composable
fun IDTextField(
    placeHolder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = "",
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Person, null)
        },
        placeholder = TextFieldPlaceHolder(placeHolder),
        onValueChange = onValueChange,
        modifier = modifier,
    )
}

@Composable
fun PWTextField(
    placeHolder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = "",
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Lock, null)
        },
        placeholder = TextFieldPlaceHolder(placeHolder),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = onValueChange,
        modifier = modifier,
    )
}
