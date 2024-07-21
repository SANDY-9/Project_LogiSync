package com.sandy.statistics.utils

import android.util.Log
import com.core.model.HeartRate
import com.sandy.statistics.model.HeartRateChartItem

internal fun List<HeartRate>.toChartItem(
    minHour: Int = 8,
    maxHour: Int = 19
): List<HeartRateChartItem> {
    val groupedByHour = groupBy { it.date.substring(11, 13).toInt() }
    val heartRateChartItems = (minHour..maxHour).map { hour ->
        groupedByHour[hour]?.run {
            val minBpm = minOf { it.bpm }
            val maxBpm = maxOf { it.bpm }
            HeartRateChartItem(hour, minBpm, maxBpm)
        } ?: HeartRateChartItem(hour, null, null)
    }.sortedBy { it.hour }
    return heartRateChartItems
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
            it.hour() == position + 8
        }
    } ?: emptyList()
}
