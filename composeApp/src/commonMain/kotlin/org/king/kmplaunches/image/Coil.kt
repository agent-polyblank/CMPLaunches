package org.king.kmplaunches.image

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger

/**
 * Returns a configured [ImageLoader] instance for loading images asynchronously.
 */
fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader
        .Builder(context)
        .crossfade(true)
        .logger(DebugLogger())
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
