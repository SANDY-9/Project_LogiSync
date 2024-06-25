package com.feature.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.feature.signup.R

// 회원가입 약관 동의
@Composable
fun Agreement(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        Text(
            text = stringResource(id = R.string.signup_agree_title),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = modifier.height(16.dp))

        var allCheck by remember { mutableStateOf(false) }
        var serviceCheck by remember { mutableStateOf(false) }
        var personalCheck by remember { mutableStateOf(false) }

        AgreeCheckBox(
            checkState = allCheck,
            onCheckChange = {
                allCheck = it
                serviceCheck = it
                personalCheck = it
            },
            title = stringResource(id = R.string.signup_agree_all)
        )

        var serviceContent by remember { mutableStateOf(false) }
        AgreeExpandableCheckBox(
            checkState = serviceCheck,
            onCheckChange = {
                serviceCheck = it
            },
            title = stringResource(id = R.string.signup_agree_service),
            expand = serviceContent,
            onExpanded = {
                serviceContent = !serviceContent
            },
            content = stringResource(id = R.string.signup_agree_content),
        )

        var personalContent by remember { mutableStateOf(false) }
        AgreeExpandableCheckBox(
            checkState = personalCheck,
            onCheckChange = {
                personalCheck = it
            },
            title = stringResource(id = R.string.signup_agree_personal),
            expand = personalContent,
            onExpanded = {
                personalContent = !personalContent
            },
            content = stringResource(id = R.string.signup_agree_content),
        )

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
    onExpanded: () -> Unit,
    content: String,
    modifier: Modifier = Modifier,
) {
    Column {
        AgreeCheckBox(
            modifier = modifier,
            checkState = checkState,
            onCheckChange = onCheckChange,
            title = title
        ) {
            IconButton(
                onClick = onExpanded
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

/*
@Preview(
    name = "Agreement",
    widthDp = 360,
    heightDp = 750,
)
@Composable
private fun PreviewAgreement() {
    Agreement()
}
*/
