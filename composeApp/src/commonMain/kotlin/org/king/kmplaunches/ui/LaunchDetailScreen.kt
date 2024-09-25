package org.king.kmplaunches.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kmplaunches.composeapp.generated.resources.Res
import kmplaunches.composeapp.generated.resources.rocket
import org.king.kmplaunches.image.NetworkImage
import org.king.kmplaunches.model.RocketLaunchExt

/**
 * Launch details screen.
 * Just passing the serialized object as there is no API endpoint for the detailed view.
 */
class LaunchDetailScreen(
    private val rocketLaunch: RocketLaunchExt,
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val nav = LocalNavigator.current
        Scaffold(
            modifier = Modifier.safeContentPadding(),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = {
                            nav!!.pop()
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to launches.")
                        }
                    },
                )
            },
        ) { innerPadding ->
            Box(Modifier.fillMaxSize().padding(innerPadding)) {
                Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
                    NetworkImage(
                        imageUrl = rocketLaunch.links.patch?.large ?: "",
                        modifier = Modifier.size(250.dp).padding(15.dp).align(CenterHorizontally),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        contentDescription = "Rocket launch patch image",
                        default = Res.drawable.rocket,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = rocketLaunch.missionName,
                            style = MaterialTheme.typography.headlineLarge,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Flight Number: ${rocketLaunch.flightNumber}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Launch Date: ${rocketLaunch.launchDateUTC}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Success: ${if (rocketLaunch.launchSuccess == true) "Yes" else "No"}")
                    rocketLaunch.details?.let {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Details: $it", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
