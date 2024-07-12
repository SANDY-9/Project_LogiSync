package com.sandy.datastore.extensions

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal suspend fun <T> DataStore<Preferences>.editPrefs(
    key: Preferences.Key<T>,
    value: T
) {
    edit { prefs -> prefs[key] = value }
}

internal fun <T> DataStore<Preferences>.getPrefs(
    key: Preferences.Key<T>,
    defaultValue: T
): Flow<T> {
    return data.map { prefs -> prefs[key] ?: defaultValue }
}
