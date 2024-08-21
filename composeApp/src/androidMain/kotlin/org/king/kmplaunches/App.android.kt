package org.king.kmplaunches

import android.app.Application
import org.king.kmplaunches.module.commonModule
import org.king.kmplaunches.module.platformModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {
    override fun onCreate() {
        startKoin{
            modules(platformModules, commonModule)
            androidContext(this@Application)
        }
        super.onCreate()
    }
}