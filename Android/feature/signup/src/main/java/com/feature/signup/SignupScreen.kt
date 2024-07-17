package com.feature.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.feature.signup.components.Agreement
import com.feature.signup.components.Check
import com.feature.signup.model.AgreementState
import com.feature.signup.model.AgreementType
import com.feature.signup.model.CheckState
import com.feature.signup.model.InputType
import com.feature.signup.model.JoiningState
import com.feature.signup.model.SignupStep

@Composable
fun SignupScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        SignupTopAppBar(
            onNavigate = { navController.navigateUp() }
        )

        SignupPhaseContent(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            phase = state.phase,
            checkState = state.check,
            onNameInputChange = viewModel::input,
            onNameInputClear = viewModel::clear,
            onTelInputChange = viewModel::input,
            onTelInputClear = viewModel::clear,
            onSignupCheck = viewModel::checkSignup,
            agreementState = state.agreement,
            onAllCheckChange = viewModel::agree,
            onServiceCheckChange = viewModel::agree,
            onPersonalCheckChange = viewModel::agree,
            onServiceExpand = viewModel::expandContent,
            onPersonalExpand = viewModel::expandContent,
            onAgreementComplete = viewModel::completeAgreement,
            joiningState = state.joining,
        )

    }
}

@Composable
private fun SignupTopAppBar(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        IconButton(
            modifier = modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterStart),
            onClick = onNavigate
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun SignupPhaseContent(
    phase: SignupStep,
    checkState: CheckState,
    onNameInputChange: (String, InputType) -> Unit,
    onNameInputClear: (InputType) -> Unit,
    onTelInputChange: (String, InputType) -> Unit,
    onTelInputClear: (InputType) -> Unit,
    onSignupCheck: () -> Unit,
    agreementState: AgreementState,
    onAllCheckChange: (Boolean, AgreementType) -> Unit,
    onServiceCheckChange: (Boolean, AgreementType) -> Unit,
    onPersonalCheckChange: (Boolean, AgreementType) -> Unit,
    onServiceExpand: (AgreementType) -> Unit,
    onPersonalExpand: (AgreementType) -> Unit,
    onAgreementComplete: () -> Unit,
    joiningState: JoiningState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when (phase) {
            SignupStep.CHECK -> Check(
                check = checkState,
                onNameInputChange = onNameInputChange,
                onNameInputClear = onNameInputClear,
                onTelInputChange = onTelInputChange,
                onTelInputClear = onTelInputClear,
                onSignupCheck = onSignupCheck,
                isInputComplete = checkState.isInputComplete,
                existId = checkState.existedId,
            )

            SignupStep.AGREEMENT -> Agreement(
                agreement = agreementState,
                onAllCheckChange = onAllCheckChange,
                onServiceCheckChange = onServiceCheckChange,
                onPersonalCheckChange = onPersonalCheckChange,
                onServiceExpand = onServiceExpand,
                onPersonalExpand = onPersonalExpand,
                isAgreeComplete = agreementState.isAgreeComplete,
                onComplete = onAgreementComplete,
            )

            else -> Unit

            /*SignupStep.JOINING -> Joining(
                joining = joiningState,
                onIdInputChange = { input ->
                    event(SignupUiEvent.InputId(input))
                },
                onIdCheck = {
                    event(SignupUiEvent.CheckId)
                },
                onPwdInputChange = { input ->
                    event(SignupUiEvent.InputPwd(input))
                },
                onPwdCheckInputChange = { input ->
                    event(SignupUiEvent.InputPwdCheck(input))
                },
                onComplete = {
                    event(SignupUiEvent.RequestSignup)
                },
            )*/
        }
    }
}

@Composable
@Preview(name = "Signup")
private fun SignupScreenPreview() {
    SignupScreen(rememberNavController())
}
