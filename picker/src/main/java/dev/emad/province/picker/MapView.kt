package dev.emad.province.picker

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MapView(
    provinces: List<Province>,
    scale: Float,
    onProvinceSelected: (Province) -> Unit,
) {
    var selectedProvince by remember { mutableStateOf<Province?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
    ) {
        provinces.forEach { province ->
            val isSelected = province == selectedProvince
            val drawableId = if (isSelected) province.selectedDrawableId else province.drawableId
            val painter = painterResource(id = drawableId)
            println("${province.name}) X: ${province.x}, Y: ${province.y}, Width: ${painter.intrinsicSize.width}, height: ${painter.intrinsicSize.height}")

            Image(
                painter = painter,
                contentDescription = "${province.name} Province",
                modifier = Modifier
                    .offset(x = province.x.dp, y = province.y.dp)
                    .clickable {
                        selectedProvince = province
                        onProvinceSelected(province)
                    }
            )
        }
    }
}

@Composable
fun IranProvinceMapView(onProvinceSelected: (Province) -> Unit) {
    val provinces = listOf(
        Province("alborz", R.drawable.alborz, R.drawable.alborz, 188.65f, 144.37f),
        Province("ardabil", R.drawable.ardabil, R.drawable.ardabil, 95.64f, 12.91f),
        Province(
            "balouchestan",
            R.drawable.balouchestan,
            R.drawable.balouchestan,
            466.49f,
            327.76f
        ),
        Province("bushehr", R.drawable.bushehr, R.drawable.bushehr, 177.34f, 375.83f),
        Province("caspian_sea", R.drawable.caspian_sea, R.drawable.caspian_sea, 150.11f, 24.82f),
        Province("charmahal", R.drawable.charmahal, R.drawable.charmahal, 169.7f, 285.32f),
        Province(
            "east_azarbayjan",
            R.drawable.east_azarbayjan,
            R.drawable.east_azarbayjan,
            36.42f,
            31.99f
        ),
        Province("esfahan", R.drawable.esfahan, R.drawable.esfahan, 161.55f, 215.88f),
        Province("fars", R.drawable.fars, R.drawable.fars, 194.53f, 321.98f),
        Province("gilan", R.drawable.gilan, R.drawable.gilan, 138.93f, 63.48f),
        Province("golestan", R.drawable.golestan, R.drawable.golestan, 298.79f, 78.37f),
        Province("hamedan", R.drawable.hamedan, R.drawable.hamedan, 104.73f, 164.33f),
        Province("hamun_lake", R.drawable.hamun_lake, R.drawable.hamun_lake, 461.57f, 469.66f),
        Province("hormozgan", R.drawable.hormozgan, R.drawable.hormozgan, 262.51f, 426.32f),
        Province("ilam", R.drawable.ilam, R.drawable.ilam, 44.37f, 227.93f),
        Province("kerman", R.drawable.kerman, R.drawable.kerman, 320.18f, 309.27f),
        Province("khuzestan", R.drawable.khuzestan, R.drawable.khuzestan, 99.33f, 267.94f),
        Province("kohgeluyeh", R.drawable.kohgeluyeh, R.drawable.kohgeluyeh, 173.34f, 330.5f),
        Province("kermanshah", R.drawable.kermanshah, R.drawable.kermanshah, 33.67f, 180.85f),
        Province("kurdistan", R.drawable.kurdistan, R.drawable.kurdistan, 43f, 135.25f),
        Province("lake_urmia", R.drawable.lake_urmia, R.drawable.lake_urmia, 32.93f, 62.76f),
        Province("lorestan", R.drawable.lorestan, R.drawable.lorestan, 80.48f, 215.89f),
        Province("markazi", R.drawable.markazi, R.drawable.markazi, 145.48f, 169.28f),
        Province("mazandaran", R.drawable.mazandaran, R.drawable.mazandaran, 192.42f, 121.37f),
        Province("namak_lake", R.drawable.namak_lake, R.drawable.namak_lake, 237.56f, 204.68f),
        Province(
            "north_khorasan",
            R.drawable.north_khorasan,
            R.drawable.north_khorasan,
            361.05f,
            71.15f
        ),
        Province(
            "persian_gulf_and_oman_sea",
            R.drawable.persian_gulf_and_oman_sea,
            R.drawable.persian_gulf_and_oman_sea,
            99.8f,
            370.17f
        ),
        Province("qazvin", R.drawable.qazvin, R.drawable.qazvin, 140.53f, 125.85f),
        Province("qom", R.drawable.qom, R.drawable.qom, 179.02f, 187.05f),
        Province(
            "razavi_khorasan",
            R.drawable.razavi_khorasan,
            R.drawable.razavi_khorasan,
            375.56f,
            92.09f
        ),
        Province("semnan", R.drawable.semnan, R.drawable.semnan, 238.61f, 108.75f),
        Province(
            "south_khorasan",
            R.drawable.south_khorasan,
            R.drawable.south_khorasan,
            399.14f,
            204.01f
        ),
        Province("tehran", R.drawable.tehran, R.drawable.tehran, 206.3f, 156.73f),
        Province(
            "west_azarbayjan",
            R.drawable.west_azarbayjan,
            R.drawable.west_azarbayjan,
            6.08f,
            3.68f
        ),
        Province("yazd", R.drawable.yazd, R.drawable.yazd, 268.9f, 190.78f),
        Province("zanjan", R.drawable.zanjan, R.drawable.zanjan, 95.65f, 109.93f),
    )

    MapView(provinces = provinces, scale = 0.4f, onProvinceSelected)
}

@Preview
@Composable
fun ShowIranProvinceMapView() {
    val context = LocalContext.current

    IranProvinceMapView {
        Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
    }
}
