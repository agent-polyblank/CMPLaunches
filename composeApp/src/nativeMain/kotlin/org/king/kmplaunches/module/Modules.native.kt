package org.king.kmplaunches.module

import org.king.kmplaunches.db.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformModules = module {
        single { DatabaseDriverFactory() }
}