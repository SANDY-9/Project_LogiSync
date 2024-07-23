package com.feature.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.core.navigation.Route
import com.feature.signup.components.Agreement
import com.feature.signup.components.Check
import com.feature.signup.components.Joining
import com.feature.signup.model.AgreementState
import com.feature.signup.model.AgreementType
import com.feature.signup.model.CheckState
import com.feature.signup.model.InputType
import com.feature.signup.model.JoiningState
import com.feature.signup.model.SignupStep

private const val NETWORK_ERROR_MESSAGE = "네트워크 연결 상태를 확인해주세요."
@Composable
fun SignupScreen(
    navController: NavController,
    onNavigate: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(state.signupComplete) {
        if (state.signupComplete) {
            val isInitialConnect = viewModel.getIsInitialConnect()
            onNavigate(isInitialConnect)
        }
    }

    LaunchedEffect(state.error) {
        if (state.error) {
            Toast.makeText(context, NETWORK_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding()
    ) {
        SignupTopAppBar(
            onNavigate = { navController.navigateUp() }
        )

        SignupPhaseContent(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            phase = state.phase,
            loading = state.loading,
            checkState = state.check,
            onInputChange = viewModel::input,
            onInputClear = viewModel::clear,
            onSignupCheck = viewModel::checkSignup,
            agreementState = state.agreement,
            onCheckChange = viewModel::agree,
            onContentExpand = viewModel::expandContent,
            onAgreementComplete = viewModel::completeAgreement,
            joiningState = state.joining,
            onIdCheck = viewModel::checkId,
            onVisibleChange = viewModel::changePwdVisible,
            onSignupComplete = viewModel::requestSignup,
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
    loading: Boolean,
    checkState: CheckState,
    onInputChange: (String, InputType) -> Unit,
    onInputClear: (InputType) -> Unit,
    onSignupCheck: () -> Unit,
    agreementState: AgreementState,
    onCheckChange: (Boolean, AgreementType) -> Unit,
    onContentExpand: (AgreementType) -> Unit,
    onAgreementComplete: () -> Unit,
    joiningState: JoiningState,
    onIdCheck: () -> Unit,
    onVisibleChange: (InputType) -> Unit,
    onSignupComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when (phase) {
            SignupStep.CHECK -> Check(
                loading = loading,
                check = checkState,
                onInputChange = onInputChange,
                onInputClear = onInputClear,
                onSignupCheck = onSignupCheck,
            )

            SignupStep.AGREEMENT -> Agreement(
                loading = loading,
                agreement = agreementState,
                onCheckChange = onCheckChange,
                onContentExpand = onContentExpand,
                isAgreeComplete = agreementState.isAgreeComplete,
                onComplete = onAgreementComplete,
            )

            SignupStep.JOINING -> Joining(
                loading = loading,
                joining = joiningState,
                onInputChange = onInputChange,
                onInputClear = onInputClear,
                onIdCheck = onIdCheck,
                onVisibleChange = onVisibleChange,
                onSignupComplete = onSignupComplete,
            )
        }
    }
}
