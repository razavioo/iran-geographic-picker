package dev.emad.geographic.picker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputFilter
import androidx.compose.ui.input.pointer.PointerInputModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    geographicEntities: List<GeographicEntity>,
    onGeographicEntitySelected: (GeographicEntity) -> Unit,
) {
    val selectedGeographicEntity = remember { mutableStateOf<GeographicEntity?>(null) }
    Box(modifier = modifier) {
        geographicEntities.forEach { entity ->
            GeographicEntityImage(
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
    geographicEntity: GeographicEntity,
    isSelected: Boolean,
    onSelect: (GeographicEntity) -> Unit,
) {
    val context = LocalContext.current
    val drawableRes =
        if (isSelected) geographicEntity.selectedDrawableId else geographicEntity.drawableId

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(drawableRes) {
        bitmap = getBitmapFromVectorDrawable(context, drawableRes)
    }

    bitmap?.let { bmp ->
        Image(
            bitmap = bmp.asImageBitmap(),
            contentDescription = geographicEntity.name,
            modifier = Modifier
                .offset(x = geographicEntity.x.dp, y = geographicEntity.y.dp)
                .transparentTouchIntercept(bitmap = bmp) {
                    onSelect(geographicEntity)
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

fun Modifier.transparentTouchIntercept(bitmap: Bitmap, onTouch: () -> Unit): Modifier = then(
    object : PointerInputModifier {
        override val pointerInputFilter = object : PointerInputFilter() {
            override fun onPointerEvent(
                pointerEvent: PointerEvent,
                pass: PointerEventPass,
                bounds: IntSize,
            ) {
                if (pass == PointerEventPass.Initial) {
                    pointerEvent.changes.forEach { change ->
                        val position = change.position
                        val x = (position.x / bounds.width * bitmap.width).toInt()
                        val y = (position.y / bounds.height * bitmap.height).toInt()

                        val pixel = bitmap.getPixel(x, y)
                        val alpha = pixel ushr 24 and 0xff
                        if (alpha == 0) {
                            change.consume()
                            onTouch()
                        }
                    }
                }
            }

            override fun onCancel() {}
        }
    }
)

@Preview(showBackground = true)
@Composable
fun PreviewIranGeographicEntityMapView() {
    IranGeographicMapView(onGeographicEntitySelected = { /* No-op for preview */ })
}
