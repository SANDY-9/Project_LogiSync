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
import com.feature.signup.components.Joining
import com.feature.signup.model.AgreementState
import com.feature.signup.model.CheckState
import com.feature.signup.model.JoiningState
import com.feature.signup.model.SignupStep
import com.feature.signup.model.SignupUiEvent

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
            phase = state.phase,
            checkState = state.check,
            agreementState = state.agreement,
            joiningState = state.joining,
            event = viewModel::onEvent,
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
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
    agreementState: AgreementState,
    joiningState: JoiningState,
    event: (SignupUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        when (phase) {
            SignupStep.CHECK -> Check(
                check = checkState,
                onNameInput = { input ->
                    event(SignupUiEvent.InputName(input))
                },
                onTelInput = { input ->
                    event(SignupUiEvent.InputTel(input))
                },
                onSignupCheck = {
                    event(SignupUiEvent.CheckSignup)
                },
            )

            SignupStep.AGREEMENT -> Agreement(
                agreement = agreementState,
                onCheck = { event(SignupUiEvent.CheckAgreement) }
            )

            SignupStep.JOINING -> Joining(
                joining = joiningState,
                onComplete = { event(SignupUiEvent.CompleteSignup) }
            )
        }
    }
}

@Composable
@Preview(name = "Signup")
private fun SignupScreenPreview() {
    SignupScreen(rememberNavController())
}
