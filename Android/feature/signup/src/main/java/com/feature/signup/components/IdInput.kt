package com.feature.signup.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.MyTextField
import com.core.desinsystem.icons.Check
import com.feature.signup.R

@Composable
internal fun IdInput(
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
            null -> MaterialTheme.colorScheme.outline
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
            contentPadding = PaddingValues(horizontal = 12.dp),
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
