@file:Suppress("ktlint:standard:filename")

package org.king.kmplaunches.store

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.io.files.Path
import org.king.kmplaunches.settings.Settings

actual class SettingsFactory {
    /**
     * Provide the Android [Context] to use when creating the [SqlDriver].
     */
    fun provideContext(context: Context) {
        this.context = context
    }

    /**
     * The Android [Context] to use when creating the [SqlDriver].
     */
    private lateinit var context: Context

    actual fun createStore(): KStore<Settings> {
        if (!this::context.isInitialized) {
            throw IllegalStateException("Context must be provided before calling createStore")
        }
        val appDir = Path(context.filesDir.path)
        return storeOf(file = Path("$appDir/settings.json"))
    }
}
