package com.core.desinsystem.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.core.desinsystem.theme.LogiBlue
import com.core.desinsystem.theme.LogiLightGray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDateRangePickerBottomSheet(
    selectedStartDateStr: String,
    selectedEndDateStr: String,
    onSelectedStartDate: (Long?) -> Unit,
    onSelectedEndDate: (Long?) -> Unit,
    onComplete: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    ModalBottomSheet(
        sheetState = bottomSheetState,
        dragHandle = null,
        onDismissRequest = onDismissRequest,
        containerColor = Color.White,
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Spacer(modifier = modifier.height(16.dp))
            MyDateRangePicker(
                selectedStartDateStr = selectedStartDateStr,
                selectedEndDateStr = selectedEndDateStr,
                onSelectedStartDate = onSelectedStartDate,
                onSelectedEndDate = onSelectedEndDate,
            )
            NextButton(
                title = "적용하기",
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }.invokeOnCompletion {
                        onComplete()
                        onDismissRequest()
                    }
                }
            )
        }
    }
}

private val currentYear = LocalDate.now().year
val minDate: LocalDate = LocalDate.of(2024, 6, 1)
val minDateMillis = minDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

@OptIn(ExperimentalMaterial3Api::class)
private val selectableDates = object : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= System.currentTimeMillis() && utcTimeMillis >= minDateMillis
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyDateRangePicker(
    selectedStartDateStr: String,
    selectedEndDateStr: String,
    onSelectedStartDate: (Long?) -> Unit,
    onSelectedEndDate: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    yearRange: IntRange = minDate.year..currentYear,
) {
    val state = rememberDateRangePickerState(
        yearRange = yearRange,
        selectableDates = selectableDates
    )

    DateRangePicker(
        modifier = modifier.height(450.dp),
        state = state,
        title = {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                text = "조회를 확인하고 싶은 날짜를 선택해주세요.",
                textAlign = TextAlign.Center,
            )
        },
        headline = {
            Text(
                modifier = modifier.padding(start = 24.dp),
                text = "$selectedStartDateStr  -  $selectedEndDateStr",
            )
        },
        showModeToggle = true,
        colors = DatePickerDefaults.colors(
            titleContentColor = Color.Black,
            headlineContentColor = MaterialTheme.colorScheme.primary,
            weekdayContentColor = Color.Black,
            subheadContentColor = Color.Black,
            disabledDayContentColor = Color.Gray,
            dayInSelectionRangeContainerColor = LogiLightGray,
            dayInSelectionRangeContentColor = LogiBlue,
            selectedDayContainerColor = MaterialTheme.colorScheme.primary
        )
    )

    LaunchedEffect(state.selectedStartDateMillis) {
        onSelectedStartDate(state.selectedStartDateMillis)
    }
    LaunchedEffect(state.selectedEndDateMillis) {
        onSelectedEndDate(state.selectedEndDateMillis)
    }
}
