package com.sandy.statistics.utils

import com.core.model.HeartRate
import com.sandy.statistics.model.HeartRateChartItem
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

internal fun List<HeartRate>.toDailyChartItem(
    minHour: Int = 8,
    maxHour: Int = 19
): List<HeartRateChartItem> {
    val groupedByHour = groupBy { it.date.hour }
    return (minHour..maxHour).map { hour ->
        groupedByHour[hour]?.run {
            val minBpm = minOf { it.bpm }
            val maxBpm = maxOf { it.bpm }
            HeartRateChartItem(hour.toString(), minBpm, maxBpm)
        } ?: HeartRateChartItem(hour.toString(), null, null)
    }.sortedBy { it.date.toInt() }
}

internal fun List<HeartRateChartItem>.lastNotNullIndex(): Int? {
    val index = indexOfLast { it.maxBpm != null }
    return if(index < 0) null else index
}

internal fun Int?.minBPM(chartItem: List<HeartRateChartItem>): Int? {
    return this?.let { position ->
        chartItem[position].minBpm
    }
}

internal fun Int?.maxBPM(chartItem: List<HeartRateChartItem>): Int? {
    return this?.let { position ->
        chartItem[position].maxBpm
    }
}

internal fun List<HeartRate>.selectRecordItem(position: Int?) : List<HeartRate> {
    return position?.let {
        filter {
            it.date.hour == position + 8
        }
    } ?: emptyList()
}

internal fun List<HeartRate>.selectRecordItem(selectDate: String?) : List<HeartRate> {
    return selectDate?.let {
        val date = it.split(".").map { it.toInt() }
        filter { data ->
            data.date.monthValue == date.first() && data.date.dayOfMonth == date.last()
        }
    } ?: emptyList()
}

internal fun Long?.localDate() : LocalDate? {
    return this?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
}

internal fun List<HeartRate>.toPeriodChartItem(startDate: LocalDate, endDate: LocalDate): List<HeartRateChartItem> {
    val allDates = generateSequence(startDate) { it.plusDays(1) }
        .takeWhile { !it.isAfter(endDate) }
        .toList()
    val groupedByDate = groupBy { it.date.toLocalDate() }
    return allDates.map { date ->
        val heartRates = groupedByDate[date]
        val minBpm = heartRates?.minOfOrNull { it.bpm }
        val maxBpm = heartRates?.maxOfOrNull { it.bpm }
        HeartRateChartItem(
            date = "${date.monthValue}.${date.dayOfMonth}",
            minBpm = minBpm,
            maxBpm = maxBpm,
        )
    }.sortedBy { it.date }
}
