package org.king.kmplaunches.store

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.io.files.Path
import net.harawata.appdirs.AppDirsFactory
import org.king.kmplaunches.const.ORGANISATION
import org.king.kmplaunches.const.PACKAGE_NAME
import org.king.kmplaunches.const.VERSION
import org.king.kmplaunches.settings.Settings

actual class SettingsFactory {
    actual fun createStore(): KStore<Settings> {
        val appDir = AppDirsFactory.getInstance().getUserDataDir(PACKAGE_NAME, VERSION, ORGANISATION)
        return storeOf(Path(appDir))
    }
}
