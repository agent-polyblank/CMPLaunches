package org.king.kmplaunches.ui

/**
 * Represents the different states of the LaunchScreen UI
 * @param T The type of data that the UI state holds.
 */
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val exception: Throwable) : UiState<Nothing>()
}