package org.king.kmplaunches.module

import io.github.xxfast.kstore.KStore
import org.king.kmplaunches.db.DatabaseDriverFactory
import org.king.kmplaunches.viewmodel.LaunchesViewModel
import org.king.kmplaunches.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Provides the platform specific modules for Android.
 */
actual val platformModules =
    module {
        single { DatabaseDriverFactory().provideContext(androidContext()) }
        single {
            org.king.kmplaunches.store.SettingsFactory().apply {
                provideContext(androidContext())
            }
        }
        single<KStore<org.king.kmplaunches.settings.Settings>> {
            get<org.king.kmplaunches.store.SettingsFactory>().createStore()
        }
        single { SettingsViewModel(get()) }
        factory { LaunchesViewModel(get()) }
    }
