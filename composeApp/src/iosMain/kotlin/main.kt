import androidx.compose.ui.window.ComposeUIViewController
import org.king.kmplaunches.di.initKoin
import org.king.kmplaunches.ui.LaunchesScreen

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) {
    LaunchesScreen()
}