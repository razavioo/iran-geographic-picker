package dev.emad.geographic.picker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    geographicEntities: List<GeographicEntity>,
    onGeographicEntitySelected: (GeographicEntity) -> Unit,
) {
    val context = LocalContext.current
    val selectedGeographicEntity = remember { mutableStateOf<GeographicEntity?>(null) }
    Box(modifier = modifier) {
        geographicEntities.forEach { entity ->
            GeographicEntityImage(
                context = context,
                geographicEntity = entity,
                isSelected = entity == selectedGeographicEntity.value,
                onSelect = {
                    selectedGeographicEntity.value = it
                    onGeographicEntitySelected(it)
                }
            )
        }
    }
}

@Composable
fun GeographicEntityImage(
    context: Context,
    geographicEntity: GeographicEntity,
    isSelected: Boolean,
    onSelect: (GeographicEntity) -> Unit,
) {
    val drawableRes =
        if (isSelected) geographicEntity.selectedDrawableId else geographicEntity.drawableId
    val bitmap = getBitmapFromVectorDrawable(context, drawableRes)

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = geographicEntity.name,
            modifier = Modifier
                .offset(x = geographicEntity.x.dp, y = geographicEntity.y.dp)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val x = (offset.x / size.width * it.width).toInt()
                        val y = (offset.y / size.height * it.height).toInt()

                        if (x in 0 until it.width && y in 0 until it.height) {
                            val pixel = it.getPixel(x, y)
                            val alpha = pixel ushr 24 and 0xff
                            if (alpha != 0) {
                                onSelect(geographicEntity)
                            }
                        }
                    }
                }
        )
    }
}

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
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun PreviewIranGeographicEntityMapView() {
    IranGeographicMapView(onGeographicEntitySelected = { /* No-op for preview */ })
}
