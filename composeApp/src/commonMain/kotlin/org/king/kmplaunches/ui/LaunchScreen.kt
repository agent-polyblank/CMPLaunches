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
import org.king.kmplaunches.image.networkImage
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
        state = pullRefreshState,
        modifier = Modifier.fillMaxSize(),
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
fun ErrorScreen(state: UiState.Error) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Error: ${state.exception.message}")
    }
}

/**
 * Display a list of launches.
 * @param state The state of the UI
 */
@Composable
fun LaunchList(state: UiState.Success<List<RocketLaunchExt>>) {
    LazyColumn {
        items(state.data) { launch ->
            Row(
                modifier = Modifier.padding(all = 16.dp).fillMaxSize().height(150.dp),
                verticalAlignment = Alignment.CenterVertically, // Center vertically
            ) {
                networkImage(
                    imageUrl = launch.links.patch?.small ?: "",
                    contentScale = ContentScale.Crop,
                    contentDescription = "Patch",
                    modifier =
                        Modifier
                            .width(100.dp)
                            .height(100.dp),
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
                        color = if (launch.launchSuccess == true) app_theme_successful else app_theme_unsuccessful,
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
