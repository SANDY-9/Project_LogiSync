package com.feature.signup.components

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.feature.signup.R
import com.feature.signup.model.CheckState

// 회원가입 여부 확인
@Composable
internal fun Check(
    check: CheckState,
    onNameInputChange: (String) -> Unit,
    onTelInputChange: (String) -> Unit,
    onSignupCheck: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.signup_check_title),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = modifier.height(16.dp))

            OutlinedTextField(
                value = check.name,
                onValueChange = onNameInputChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = R.string.signup_check_name_label))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.signup_check_name_placeholder))
                },
                singleLine = true,
            )

            Spacer(modifier = modifier.height(12.dp))

            OutlinedTextField(
                value = check.tel,
                onValueChange = onTelInputChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Call,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.signup_check_tel_label)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.signup_check_tel_placeholder)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
                singleLine = true,
            )
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onSignupCheck
        ) {
            Text(
                text = stringResource(id = R.string.signup_next_step1)
            )

        }
    }
}
