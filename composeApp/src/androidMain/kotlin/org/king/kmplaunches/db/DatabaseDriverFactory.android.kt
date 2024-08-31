package org.king.kmplaunches.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

/**
 * Provides a [SqlDriver] for Android.
 */
actual class DatabaseDriverFactory {
    /**
     * Provide the Android [Context] to use when creating the [SqlDriver].
     */
    fun provideContext(context: Context): DatabaseDriverFactory =
        DatabaseDriverFactory().apply {
            this.context = context
        }

    /**
     * The Android [Context] to use when creating the [SqlDriver].
     */
    private lateinit var context: Context

    /**
     * Create a new [SqlDriver] for Android.
     */
    actual fun createDriver(): SqlDriver {
        if (!this::context.isInitialized) {
            throw IllegalStateException("Context must be provided before calling createDriver")
        }
        return AndroidSqliteDriver(AppDatabase.Schema, context, "spacex.db")
    }
}
