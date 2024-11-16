package org.king.kmplaunches.store

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.io.files.Path
import org.king.kmplaunches.settings.Settings
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class SettingsFactory {
    @OptIn(ExperimentalForeignApi::class)
    actual fun createStore(): KStore<Settings> {
        val fileManager: NSFileManager = NSFileManager.defaultManager
        val documentsUrl: NSURL =
            fileManager.URLForDirectory(
                directory = NSDocumentDirectory,
                appropriateForURL = null,
                create = false,
                inDomain = NSUserDomainMask,
                error = null,
            )!!
        return storeOf(file = Path(documentsUrl.path!!))
    }
}
