package org.king.kmplaunches.module

import org.king.kmplaunches.db.DatabaseDriverFactory
import org.king.kmplaunches.store.SettingsFactory
import org.king.kmplaunches.viewmodel.LaunchesViewModel
import org.king.kmplaunches.viewmodel.SettingsViewModel
import org.koin.dsl.module

actual val platformModules =
    module {
        single { DatabaseDriverFactory() }
        single { SettingsFactory() }
        single { LaunchesViewModel(get()) }
        single { SettingsViewModel(get()) }
    }
