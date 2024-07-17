package com.feature.signup.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.feature.signup.R
import com.feature.signup.model.AgreementState
import com.feature.signup.model.AgreementType

// 회원가입 약관 동의
@Composable
internal fun Agreement(
    agreement: AgreementState,
    onCheckChange: (Boolean, AgreementType) -> Unit,
    onContentExpand: (AgreementType) -> Unit,
    isAgreeComplete: Boolean,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {

            Text(
                text = stringResource(id = R.string.signup_agree_title),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = modifier.height(16.dp))

            AgreeCheckBox(
                checkState = agreement.isAllChecked,
                onCheckChange = {
                    onCheckChange(it, AgreementType.ALL)
                },
                title = stringResource(id = R.string.signup_agree_all)
            )

            AgreeExpandableCheckBox(
                checkState = agreement.isServiceChecked,
                onCheckChange = {
                    onCheckChange(it, AgreementType.SERVICE)
                },
                title = stringResource(id = R.string.signup_agree_service),
                expand = agreement.isServiceExpand,
                onExpand = {
                    onContentExpand(AgreementType.SERVICE)
                },
                content = stringResource(id = R.string.signup_agree_content),
            )

            AgreeExpandableCheckBox(
                checkState = agreement.isPersonalChecked,
                onCheckChange = {
                    onCheckChange(it, AgreementType.PERSONAL)
                },
                title = stringResource(id = R.string.signup_agree_personal),
                expand = agreement.isPersonalExpand,
                onExpand = {
                    onContentExpand(AgreementType.PERSONAL)
                },
                content = stringResource(id = R.string.signup_agree_content),
            )
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = isAgreeComplete,
            onClick = onComplete,
        ) {
            Text(
                text = stringResource(id = R.string.signup_next_step2)
            )
        }
    }
}

@Composable
private fun AgreeCheckBox(
    checkState: Boolean,
    onCheckChange: (Boolean) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkState,
            onCheckedChange = onCheckChange,
        )
        Text(
            modifier = modifier.weight(1f),
            text = title,
        )
        content.invoke()
    }
}

private val arrowRight = Icons.AutoMirrored.Rounded.KeyboardArrowRight
private val arrowUp = Icons.Rounded.KeyboardArrowUp

@Composable
private fun AgreeExpandableCheckBox(
    checkState: Boolean,
    onCheckChange: (Boolean) -> Unit,
    title: String,
    expand: Boolean,
    onExpand: () -> Unit,
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.animateContentSize()
    ) {
        AgreeCheckBox(
            checkState = checkState,
            onCheckChange = onCheckChange,
            title = title
        ) {
            IconButton(
                onClick = onExpand
            ) {
                Icon(
                    imageVector = if (expand) arrowUp else arrowRight,
                    contentDescription = null
                )
            }
        }
        if (expand) {
            Text(text = content)
        }
    }
}
