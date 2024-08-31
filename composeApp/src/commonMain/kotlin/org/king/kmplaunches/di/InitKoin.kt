package org.king.kmplaunches.di

import org.king.kmplaunches.module.commonModule
import org.king.kmplaunches.module.platformModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Initializes Koin with platform and common modules.
 */
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(platformModules, commonModule)
    }
}
