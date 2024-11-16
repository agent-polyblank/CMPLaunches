package org.king.kmplaunches.store

import io.github.xxfast.kstore.KStore
import org.king.kmplaunches.settings.Settings

expect class SettingsFactory {
    fun createStore(): KStore<Settings>
}
