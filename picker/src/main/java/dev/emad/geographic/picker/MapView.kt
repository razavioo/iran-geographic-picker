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

@ExperimentalComposeUiApi
@Composable
fun MapView(
    modifier: Modifier = Modifier,
    geographicEntities: List<GeographicEntity>,
    onGeographicEntitySelected: (GeographicEntity) -> Unit,
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    var selectedGeographicEntity by remember { mutableStateOf<GeographicEntity?>(null) }

    val touchModifier = Modifier.pointerInteropFilter { event ->
        val touchX = event.x
        val touchY = event.y

        geographicEntities.forEach { entity ->
            val bitmap = getBitmapFromVectorDrawable(context, entity.drawableId)
            bitmap?.let { bmp ->
                val entityXInPx = with(density) { entity.x.dp.toPx() }
                val entityYInPx = with(density) { entity.y.dp.toPx() }

                val adjustedX = touchX - entityXInPx
                val adjustedY = touchY - entityYInPx

                if (adjustedX in 0f..bmp.width.toFloat() && adjustedY in 0f..bmp.height.toFloat()) {
                    val pixel = bmp.getPixel(adjustedX.toInt(), adjustedY.toInt())
                    val alpha = pixel ushr 24 and 0xff
                    if (alpha != 0) {
                        selectedGeographicEntity = entity
                        onGeographicEntitySelected(entity)
                        return@pointerInteropFilter true
                    }
                }
            }
        }
        false
    }

    Box(modifier = modifier.then(touchModifier)) {
        geographicEntities.forEach { entity ->
            GeographicEntityImage(
                geographicEntity = entity,
                isSelected = entity == selectedGeographicEntity
            )
        }
    }
}

@Composable
fun GeographicEntityImage(
    geographicEntity: GeographicEntity,
    isSelected: Boolean,
) {
    val context = LocalContext.current
    val drawableRes =
        if (isSelected) geographicEntity.selectedDrawableId else geographicEntity.drawableId
    val bitmap = remember(drawableRes) { getBitmapFromVectorDrawable(context, drawableRes) }

    bitmap?.asImageBitmap()?.let { bmp ->
        Image(
            bitmap = bmp,
            contentDescription = geographicEntity.name,
            modifier = Modifier.offset(x = geographicEntity.x.dp, y = geographicEntity.y.dp)
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun IranGeographicMapView(
    modifier: Modifier = Modifier,
    onGeographicEntitySelected: (GeographicEntity) -> Unit,
) {
    MapView(
        modifier = modifier,
        geographicEntities = geographicEntities,
        onGeographicEntitySelected = onGeographicEntitySelected
    )
}

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

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun PreviewIranGeographicEntityMapView() {
    IranGeographicMapView(onGeographicEntitySelected = { /* No-op for preview */ })
}
