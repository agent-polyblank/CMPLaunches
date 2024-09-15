@file:Suppress("ktlint:standard:filename", "ktlint:standard:function-naming")

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import org.king.kmplaunches.di.initKoin
import org.king.kmplaunches.ui.LaunchScreen

fun MainViewController() =
    ComposeUIViewController(configure = {
        initKoin()
    }) {
        Navigator(LaunchScreen())
    }
