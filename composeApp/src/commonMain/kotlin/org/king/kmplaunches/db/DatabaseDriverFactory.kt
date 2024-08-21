package org.king.kmplaunches.db

import app.cash.sqldelight.db.SqlDriver

/**
 * Provides a [SqlDriver] for the current platform.
 */
expect class DatabaseDriverFactory() {
    fun createDriver(): SqlDriver
}