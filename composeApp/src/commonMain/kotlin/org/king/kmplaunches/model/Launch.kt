package org.king.kmplaunches.model

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a rocket launch.
 */
@Serializable
data class RocketLaunchExt(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("name")
    val missionName: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    @SerialName("details")
    val details: String?,
    @SerialName("success")
    val launchSuccess: Boolean?,
    @SerialName("links")
    val links: Links,
) {
    var launchYear = Instant.parse(launchDateUTC).toLocalDateTime(TimeZone.UTC).year
}

/**
 * Represents the links for a rocket launch.
 */
@Serializable
data class Links(
    @SerialName("patch")
    val patch: Patch?,
    @SerialName("article")
    val article: String?,
)

/**
 * Represents the patch for a rocket launch.
 */
@Serializable
data class Patch(
    @SerialName("small")
    val small: String?,
    @SerialName("large")
    val large: String?,
)
