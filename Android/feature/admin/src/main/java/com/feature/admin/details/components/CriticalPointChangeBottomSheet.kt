package com.feature.admin.details.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.desinsystem.common.MyNumberTextField
import com.core.desinsystem.common.NextButton
import com.core.desinsystem.common.addFocusCleaner
import com.feature.admin.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriticalPointChangeBottomSheet(
    min: String,
    max: String,
    onMinInputChange: (String) -> Unit,
    onMaxInputChange: (String) -> Unit,
    onComplete: () -> Unit,
    onDismissRequest: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalBottomSheet(
        sheetState = bottomSheetState,
        dragHandle = null,
        onDismissRequest = onDismissRequest,
        containerColor = Color.White,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .addFocusCleaner(focusManager),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = modifier.padding(16.dp),
                text = stringResource(id = R.string.details_critical_point),
                style = MaterialTheme.typography.labelLarge,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MyNumberTextField(
                    modifier = modifier
                        .width(125.dp)
                        .addFocusCleaner(focusManager),
                    value = min,
                    onValueChange = onMinInputChange,
                    placeholder = {
                        Text(text = stringResource(id = R.string.details_critical_point_min))
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                    ),
                    focusManager = focusManager,
                )
                Spacer(modifier = modifier
                    .padding(horizontal = 12.dp)
                    .height(2.dp)
                    .width(10.dp)
                    .background(color = Color.DarkGray))
                MyNumberTextField(
                    modifier = modifier
                        .width(125.dp)
                        .addFocusCleaner(focusManager),
                    value = max,
                    onValueChange = onMaxInputChange,
                    placeholder = {
                        Text(text = stringResource(id = R.string.details_critical_point_max))
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                    ),
                    focusManager = focusManager,
                )
            }
            NextButton(
                title = "변경하기",
                onClick = {
                    if (min.isBlank() || max.isBlank()) {
                        Toast.makeText(context, "빈 칸을 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            onComplete()
                            onDismissRequest()
                        }
                    }
                }
            )
        }
    }
}
