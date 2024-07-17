package com.sandy.statistics.utils

import java.time.LocalDate
import java.time.ZoneId

internal val minDate: LocalDate = LocalDate.of(2024, 6, 1)
internal val minDateMillis = minDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
