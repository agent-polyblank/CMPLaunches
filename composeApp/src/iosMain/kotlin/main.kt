@file:Suppress("ktlint:standard:filename", "ktlint:standard:function-naming")

import androidx.compose.ui.window.ComposeUIViewController
import org.king.kmplaunches.di.initKoin
import org.king.kmplaunches.ui.LaunchesScreen

fun MainViewController() =
    ComposeUIViewController(configure = {
        initKoin()
    }) {
        LaunchesScreen()
    }
