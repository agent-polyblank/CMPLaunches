package org.king.kmplaunches.module

import org.king.kmplaunches.db.DatabaseDriverFactory
import org.king.kmplaunches.store.SettingsFactory
import org.king.kmplaunches.viewmodel.LaunchesViewModel
import org.koin.dsl.module

actual val platformModules =
    module {
        single { DatabaseDriverFactory() }
        single { SettingsFactory() }
        factory { LaunchesViewModel(get()) }
    }
