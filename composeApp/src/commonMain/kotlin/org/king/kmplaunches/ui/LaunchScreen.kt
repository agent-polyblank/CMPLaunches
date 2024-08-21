package org.king.kmplaunches.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.king.kmplaunches.image.NetworkImage
import org.king.kmplaunches.model.Links
import org.king.kmplaunches.model.Patch
import org.king.kmplaunches.model.RocketLaunchExt
import org.king.kmplaunches.theme.app_theme_successful
import org.king.kmplaunches.theme.app_theme_unsuccessful
import org.king.kmplaunches.viewmodel.LaunchesViewModel
import org.koin.compose.koinInject

/**
 * The main screen of the app that displays a list of launches.
 * @param viewModel The ViewModel holding the state for the launch screen.
 */
@Composable
fun LaunchesScreen(viewModel: LaunchesViewModel = koinInject()) {
    val uiState = viewModel.launches.collectAsState()
    val isRefreshing = uiState.value is UiState.Loading

    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { viewModel.refresh() })

    PullRefreshLayout(
            state = pullRefreshState, modifier = Modifier.fillMaxSize()
        ) {
            when (val state = uiState.value) {
                is UiState.Loading -> {
                    LoadingScreen()
                }
                is UiState.Success -> {
                    LaunchList(state)
                }
                is UiState.Error -> {
                    ErrorScreen(state)
                }
            }
        }
    }

/**
 * Display an error message.
 * @param state The state of the UI
 */
@Composable
private fun ErrorScreen(state: UiState.Error) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Error: ${state.exception.message}")
    }
}

/**
 * Display a list of launches.
 * @param state The state of the UI
 */
@Composable
private fun LaunchList(state: UiState.Success<List<RocketLaunchExt>>) {
    LazyColumn {
        items(state.data) { launch ->
            Row(
                modifier = Modifier.padding(all = 16.dp).fillMaxSize().height(150.dp),
                verticalAlignment = Alignment.CenterVertically // Center vertically
            ) {
                NetworkImage(
                    imageUrl = launch.links.patch?.small ?: "",
                    contentScale = ContentScale.Crop,
                    contentDescription = "Patch",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "${launch.missionName} - ${launch.launchYear}",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = if (launch.launchSuccess == true) "Successful" else "Unsuccessful",
                        color = if (launch.launchSuccess == true) app_theme_successful else app_theme_unsuccessful
                    )
                    Spacer(Modifier.height(8.dp))
                    val details = launch.details
                    if (details?.isNotBlank() == true) {
                        Text(text = details, overflow = TextOverflow.Ellipsis, maxLines = 2)
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
            HorizontalDivider()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun LaunchesErrorScreenPreview() {
    ErrorScreen(UiState.Error(Exception("An error occurred")))
}

@Preview
@Composable
fun LaunchesScreenLoadingPreview() {
    LoadingScreen()
}

@Preview
@Composable
fun LaunchesScreenPreview() {
    val sampleLaunches = listOf(
        RocketLaunchExt(
            flightNumber = 1,
            missionName = "FalconSat",
            launchDateUTC = "2006-03-24T22:30:00.000Z",
            details = "Engine failure at 33 seconds and loss of vehicle",
            launchSuccess = false,
            links = Links(
                patch = Patch(
                    small = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png",
                    large = "https://images2.imgbox.com/40/e3/GypSkayF_o.png"
                ),
                article = "https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html"
            )
        ),
        RocketLaunchExt(
            flightNumber = 2,
            missionName = "DemoSat",
            launchDateUTC = "2007-03-21T01:10:00.000Z",
            details = "Successful first stage burn and transition to second stage, but the second stage did not reach orbit",
            launchSuccess = false,
            links = Links(
                patch = Patch(
                    small = "https://images2.imgbox.com/4b/bd/d8UxLh4q_o.png",
                    large = "https://images2.imgbox.com/80/a2/bkWotCIS_o.png"
                ),
                article = "https://www.space.com/2299-spacex-falcon-1-rocket-fails-reach-orbit.html"
            )
        )
    )
    LaunchList(UiState.Success(sampleLaunches))
}