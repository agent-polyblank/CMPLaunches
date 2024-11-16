package org.king.kmplaunches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.Navigator
import org.king.kmplaunches.settings.Settings
import org.king.kmplaunches.theme.AppTheme
import org.king.kmplaunches.ui.LaunchScreen
import org.king.kmplaunches.viewmodel.SettingsViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settings by settingsViewModel.settings.collectAsState(Settings())

            AppTheme(settings = settings!!) {
                Navigator(screen = LaunchScreen())
            }
        }
    }
}
