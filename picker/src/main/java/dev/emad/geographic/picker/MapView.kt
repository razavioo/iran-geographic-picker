package dev.emad.geographic.picker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

/**
 * Main view for displaying a map composed of geographic entities.
 * Handles touch events to determine selection based on transparency.
 *
 * @param modifier Modifier to be applied to the Box container.
 * @param geographicEntities List of geographic entities to display.
 * @param onGeographicEntitySelected Callback when an entity is selected.
 */
@ExperimentalComposeUiApi
@Composable
fun MapView(
    modifier: Modifier = Modifier,
    geographicEntities: List<GeographicEntity>,
    onGeographicEntitySelected: (GeographicEntity) -> Unit,
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    // State to track the currently selected geographic entity.
    var selectedGeographicEntity by remember { mutableStateOf<GeographicEntity?>(null) }

    // Modifier for handling touch events on the map.
    val touchModifier = Modifier.pointerInteropFilter { event ->
        val touchX = event.x
        val touchY = event.y

        // Iterate through entities to find if the touch point hits a non-transparent pixel.
        geographicEntities.forEach { entity ->
            val bitmap = getBitmapFromVectorDrawable(context, entity.drawableId)
            bitmap?.let { bmp ->
                // Adjust touch coordinates relative to the entity's position.
                val entityXInPx = with(density) { entity.x.dp.toPx() }
                val entityYInPx = with(density) { entity.y.dp.toPx() }
                val adjustedX = touchX - entityXInPx
                val adjustedY = touchY - entityYInPx

                // Check if the adjusted touch point hits a non-transparent pixel.
                if (adjustedX in 0f..bmp.width.toFloat() && adjustedY in 0f..bmp.height.toFloat()) {
                    val pixel = bmp.getPixel(adjustedX.toInt(), adjustedY.toInt())
                    val alpha = pixel ushr 24 and 0xff
                    if (alpha != 0) {
                        // Update the selected entity and trigger the callback.
                        selectedGeographicEntity = entity
                        onGeographicEntitySelected(entity)
                        return@pointerInteropFilter true
                    }
                }
            }
        }
        false
    }

    // Container for geographic entity images.
    Box(modifier = modifier.then(touchModifier)) {
        geographicEntities.forEach { entity ->
            GeographicEntityImage(
                geographicEntity = entity,
                isSelected = entity == selectedGeographicEntity
            )
        }
    }
}

/**
 * Displays an image for a geographic entity.
 *
 * @param geographicEntity The entity to display.
 * @param isSelected Whether the entity is currently selected.
 */
@Composable
fun GeographicEntityImage(
    geographicEntity: GeographicEntity,
    isSelected: Boolean,
) {
    val context = LocalContext.current
    // Determine the appropriate drawable based on selection state.
    val drawableRes =
        if (isSelected) geographicEntity.selectedDrawableId else geographicEntity.drawableId
    // Load the bitmap for the drawable resource.
    val bitmap = remember(drawableRes) { getBitmapFromVectorDrawable(context, drawableRes) }

    bitmap?.asImageBitmap()?.let { bmp ->
        // Display the bitmap with an offset corresponding to the entity's position.
        Image(
            bitmap = bmp,
            contentDescription = geographicEntity.name,
            modifier = Modifier.offset(x = geographicEntity.x.dp, y = geographicEntity.y.dp)
        )
    }
}

/**
 * Represents a geographic map view specifically for displaying entities within Iran.
 * This composable acts as a specialized wrapper around the more generic [MapView], pre-configuring
 * it with data and behaviors relevant to the Iranian geographic context.
 *
 * @param modifier A [Modifier] applied to the map view for customization.
 * @param onGeographicEntitySelected Callback invoked when a geographic entity on the map is selected.
 *
 * Note: The usage of [ExperimentalComposeUiApi] indicates that this composable relies on APIs
 * that are still under experimentation and may change in the future. Be cautious of potential
 * changes when updating Compose dependencies.
 */
@ExperimentalComposeUiApi
@Composable
fun IranGeographicMapView(
    modifier: Modifier = Modifier,
    onGeographicEntitySelected: (GeographicEntity) -> Unit,
) {
    // Initialize the list of geographic entities specific to Iran.
    val iranGeographicEntities = geographicEntities

    // Use the generic MapView composable to render the map, passing in the Iranian geographic entities.
    // The `onGeographicEntitySelected` callback is provided to handle selection events on the map.
    MapView(
        modifier = modifier,
        geographicEntities = iranGeographicEntities,
        onGeographicEntitySelected = onGeographicEntitySelected
    )
}

/**
 * Converts a vector drawable resource into a Bitmap.
 *
 * @param context The context used to access resources.
 * @param drawableId The resource ID of the drawable to convert.
 * @return A Bitmap representation of the drawable, or null if conversion fails.
 */
fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(context, drawableId)
    drawable?.let {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
    return null
}

/**
 * A preview composable for the IranGeographicMapView. This serves as a demonstration
 * of how the MapView would look with a predefined selection callback.
 */
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun PreviewIranGeographicEntityMapView() {
    IranGeographicMapView(onGeographicEntitySelected = { /* No-op for preview */ })
}
