package com.feature.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.desinsystem.common.addFocusCleaner
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
    onVisibleChange: (InputType) -> Unit,
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

            PwdInput(
                modifier = modifier.fillMaxWidth(),
                pwd = joining.pwd,
                pwdCheck = joining.pwdCheck,
                onInputChange = onInputChange,
                pwdVisible = joining.pwdVisible,
                pwdCheckVisible = joining.pwdCheckVisible,
                onVisibleChange = onVisibleChange,
                isNotValidPwd = joining.isNotValidPwd,
                isNotValidPwdCheck = joining.isNotValidPwdCheck,
                focusManager = focusManager,
            )
        }

        val isSingUpEnabled = joining.isNotValidPwdCheck == false && joining.existedId == false
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = isSingUpEnabled,
            onClick = onSignupComplete,
        ) {
            Text(
                text = stringResource(id = R.string.signup_next_step3)
            )
        }
    }
}
