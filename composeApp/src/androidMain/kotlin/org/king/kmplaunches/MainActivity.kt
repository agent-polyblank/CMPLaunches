package org.king.kmplaunches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.king.kmplaunches.theme.AppTheme
import org.king.kmplaunches.ui.LaunchesScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme{
                LaunchesScreen()
            }
        }
    }
}