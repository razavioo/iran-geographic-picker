package dev.emad.geographic.picker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    val drawableRes =
        if (isSelected) geographicEntity.selectedDrawableId else geographicEntity.drawableId
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = geographicEntity.name,
        modifier = Modifier
            .offset(x = geographicEntity.x.dp, y = geographicEntity.y.dp)
            .clickable { onSelect(geographicEntity) }
    )
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

@Preview(showBackground = true)
@Composable
fun PreviewIranGeographicEntityMapView() {
    IranGeographicMapView(onGeographicEntitySelected = { /* No-op for preview */ })
}
