package org.king.kmplaunches.viewmodel

import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.king.kmplaunches.settings.Settings

/**
 * ViewModel for the SettingsScreen.
 * @param settingsStore The store for the settings.
 */
class SettingsViewModel(
    private val settingsStore: KStore<Settings>,
) {
    val settings: Flow<Settings?> = settingsStore.updates

    init {
        val defaultSettings = Settings() // Define your default settings here
        CoroutineScope(Dispatchers.IO).launch {
            updateSettings(defaultSettings)
        }
    }

    /**
     * Update the settings.
     * @param newSettings The new settings.
     */
    suspend fun updateSettings(newSettings: Settings?) {
        settingsStore.update { newSettings }
    }
}
