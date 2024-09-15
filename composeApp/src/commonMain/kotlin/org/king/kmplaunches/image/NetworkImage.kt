package org.king.kmplaunches.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest

/**
 * A composable that loads an image from a URL and displays it.
 *
 * @param imageUrl The URL of the image to load.
 * @param modifier The modifier to apply to the image.
 * @param contentScale The scale at which the image should be displayed.
 * @param contentDescription The content description of the image.
 */
@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    contentDescription: String? = null,
) {
    SubcomposeAsyncImage(
        imageLoader = getAsyncImageLoader(LocalPlatformContext.current),
        model =
            ImageRequest
                .Builder(LocalPlatformContext.current)
                .data(imageUrl)
                .build(),
        loading = {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(32.dp),
                )
            }
        },
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
    )
}
