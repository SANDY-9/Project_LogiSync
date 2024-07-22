package com.core.desinsystem.common

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Clear
import com.core.desinsystem.icons.Visibility
import com.core.desinsystem.icons.Visibilityoff

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}
@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onValueClear: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.addFocusCleaner(focusManager).background(
            color = Color.White,
            shape = RoundedCornerShape(8.dp)
        ),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (value.isNotBlank()) {
                IconButton(
                    onClick = {
                        focusManager.moveFocus(FocusDirection.Previous)
                        onValueClear()
                    }) {
                    Icon(
                        imageVector = Icons.Clear,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                }
            }
        },
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                if (keyboardActions == null) {
                    focusManager.moveFocus(FocusDirection.Next)
                }
                else {
                    keyboardActions.invoke()
                }
            }
        ),
        singleLine = true,
        maxLines = 1,
        minLines = 1,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(8.dp),
        colors = colors,
    )
}
@Composable
fun MyPwdTextField(
    value: String,
    onValueChange: (String) -> Unit,
    inputVisible: Boolean,
    onInputVisibleChange: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardActions: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    val visualIcon = if (inputVisible) Icons.Visibility else Icons.Visibilityoff
    val visualTransformation = if (inputVisible) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.addFocusCleaner(focusManager).background(
            color = Color.White,
            shape = RoundedCornerShape(8.dp)
        ),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(
                onClick = {
                    focusManager.moveFocus(FocusDirection.Previous)
                    onInputVisibleChange()
                }) {
                Icon(
                    imageVector = visualIcon,
                    tint = Color.Gray,
                    contentDescription = null
                )
            }
        },
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                if (keyboardActions == null) {
                    focusManager.moveFocus(FocusDirection.Next)
                }
                else {
                    keyboardActions.invoke()
                }
            }
        ),
        singleLine = true,
        maxLines = 1,
        minLines = 1,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(8.dp),
        colors = colors,
    )
}
@Preview
@Composable
private fun TextFieldPreview() {
    var query by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    MyPwdTextField(
        value = query,
        onValueChange = { query = it },
        inputVisible = visible,
        onInputVisibleChange = { visible = !visible },
        focusManager = focusManager
    )
}
