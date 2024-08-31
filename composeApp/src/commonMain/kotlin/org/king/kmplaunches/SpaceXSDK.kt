package org.king.kmplaunches

import SpaceXAPI
import org.king.kmplaunches.db.AppDatabase
import org.king.kmplaunches.db.DatabaseDriverFactory
import org.king.kmplaunches.extension.toRocketLaunchExt
import org.king.kmplaunches.model.RocketLaunchExt

/**
 * SDK for interacting with SpaceX API and database.
 */
class SpaceXSDK(
    databaseDriverFactory: DatabaseDriverFactory,
    private val api: SpaceXAPI,
) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())

    /**
     * Get all rocket launches.
     * @param forceReload Force a reload of the from the API.
     */
    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunchExt> {
        val cachedLaunches = database.appDatabaseQueries.selectAll().executeAsList()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches.map { it.toRocketLaunchExt() }
        } else {
            api.getAllLaunches().also {
                database.appDatabaseQueries.transaction {
                    it.forEach { launch: RocketLaunchExt ->
                        database.appDatabaseQueries.insertLaunch(
                            flightNumber = launch.flightNumber.toLong(),
                            missionName = launch.missionName,
                            details = launch.details,
                            launchSuccess = launch.launchSuccess ?: false,
                            launchDateUTC = launch.launchDateUTC,
                            patchUrlSmall = launch.links.patch?.small,
                            patchUrlLarge = launch.links.patch?.large,
                            articleUrl = launch.links.article,
                        )
                    }
                }
            }
        }
    }
}
