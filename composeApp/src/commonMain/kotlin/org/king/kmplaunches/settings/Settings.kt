package org.king.kmplaunches.settings

import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val darkMode: Boolean = false,
)
