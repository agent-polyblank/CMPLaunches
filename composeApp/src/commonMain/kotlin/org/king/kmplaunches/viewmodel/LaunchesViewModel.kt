package org.king.kmplaunches.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.king.kmplaunches.SpaceXSDK
import org.king.kmplaunches.model.RocketLaunchExt
import org.king.kmplaunches.ui.UiState

/**
 * ViewModel for the LaunchesScreen.
 */
class LaunchesViewModel(
    private val sdk: SpaceXSDK,
) : ViewModel() {
    private val _launches = MutableStateFlow<UiState<List<RocketLaunchExt>>>(UiState.Loading)
    val launches: StateFlow<UiState<List<RocketLaunchExt>>> = _launches

    init {
        getLaunches()
    }

    /**
     * Refresh the list of launches.
     */
    fun refresh() {
        getLaunches(forceReload = true)
    }

    /**
     * Get the list of launches.
     * @param forceReload Force a reload of the data.
     */
    private fun getLaunches(forceReload: Boolean = false) {
        viewModelScope.launch {
            _launches.value = UiState.Loading
            try {
                val data = sdk.getLaunches(forceReload = forceReload)
                _launches.value = UiState.Success(data)
            } catch (e: Exception) {
                _launches.value = UiState.Error(e)
            }
        }
    }
}
