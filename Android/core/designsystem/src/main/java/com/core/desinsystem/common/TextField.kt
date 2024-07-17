package com.core.desinsystem.common

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.desinsystem.icons.Clear

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
        value = TextFieldValue(
            text = value,
            selection = TextRange(value.length)
        ),
        onValueChange = {
            onValueChange(it.text)
        },
        modifier = modifier.addFocusCleaner(focusManager),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (value.isNotBlank()) {
                IconButton(onClick = onValueClear) {
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

@Preview
@Composable
private fun TextFieldPreview() {
    var query: String by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    MyTextField(
        value = query,
        onValueChange = { query = it },
        onValueClear = { query = "" },
        focusManager = focusManager,

        )
}
