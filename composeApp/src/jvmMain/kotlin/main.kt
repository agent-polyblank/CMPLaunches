@file:Suppress("ktlint:standard:filename", "ktlint:standard:function-naming")

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.king.kmplaunches.module.commonModule
import org.king.kmplaunches.module.platformModules
import org.king.kmplaunches.ui.LaunchesScreen
import org.koin.core.context.startKoin
import java.awt.Dimension

fun main() =
    application {
        startKoin {
            modules(commonModule, platformModules)
        }
        Window(
            title = "Multiplatform App",
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(350, 600)
            LaunchesScreen()
        }
    }

@Preview
@Composable
fun AppPreview() {
    LaunchesScreen()
}
