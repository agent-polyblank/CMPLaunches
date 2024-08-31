package org.king.kmplaunches.extension

import org.king.kmplaunches.db.RocketLaunch
import org.king.kmplaunches.model.Links
import org.king.kmplaunches.model.Patch
import org.king.kmplaunches.model.RocketLaunchExt

/**
 * Extension function to convert a [RocketLaunch] to a [RocketLaunchExt].
 */
fun RocketLaunch.toRocketLaunchExt(): RocketLaunchExt =
    RocketLaunchExt(
        flightNumber = this.flightNumber.toInt(),
        missionName = this.missionName,
        launchDateUTC = this.launchDateUTC,
        details = this.details,
        launchSuccess = this.launchSuccess,
        links = Links(patch = Patch(this.patchUrlSmall, this.patchUrlLarge), this.articleUrl),
    )
