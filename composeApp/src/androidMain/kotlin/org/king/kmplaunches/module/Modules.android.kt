package org.king.kmplaunches.module

//import io.ktor.client.plugins.logging.DEFAULT
import org.king.kmplaunches.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Provides the platform specific modules for Android.
 */
actual val platformModules = module {
    single { DatabaseDriverFactory().provideContext(androidContext()) }
}