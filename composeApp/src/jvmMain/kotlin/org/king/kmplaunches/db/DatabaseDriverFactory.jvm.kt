package org.king.kmplaunches.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

/**
 * Provides a [SqlDriver] for the current platform.
 */
actual class DatabaseDriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        val jdbcSqliteDriver = JdbcSqliteDriver("jdbc:sqlite:compose.db")
        AppDatabase.Schema.create(driver = jdbcSqliteDriver)
        return jdbcSqliteDriver
    }
}
