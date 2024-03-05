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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    provinces: List<Province>,
    onProvinceSelected: (Province) -> Unit,
) {
    var selectedProvince by remember { mutableStateOf<Province?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
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
        Province("alborz", R.drawable.alborz, R.drawable.alborz, 94.325f, 72.185f),
        Province("ardabil", R.drawable.ardabil, R.drawable.ardabil, 47.82f, 6.455f),
        Province(
            "balouchestan",
            R.drawable.balouchestan,
            R.drawable.balouchestan,
            233.245f,
            163.88f
        ),
        Province("bushehr", R.drawable.bushehr, R.drawable.bushehr, 88.67f, 187.915f),
        Province("caspian_sea", R.drawable.caspian_sea, R.drawable.caspian_sea, 75.055f, 12.41f),
        Province("charmahal", R.drawable.charmahal, R.drawable.charmahal, 84.85f, 142.66f),
        Province(
            "east_azarbayjan",
            R.drawable.east_azarbayjan,
            R.drawable.east_azarbayjan,
            18.21f,
            15.995f
        ),
        Province("esfahan", R.drawable.esfahan, R.drawable.esfahan, 80.775f, 107.94f),
        Province("fars", R.drawable.fars, R.drawable.fars, 97.265f, 160.99f),
        Province("gilan", R.drawable.gilan, R.drawable.gilan, 69.465f, 31.74f),
        Province("golestan", R.drawable.golestan, R.drawable.golestan, 149.395f, 39.185f),
        Province("hamedan", R.drawable.hamedan, R.drawable.hamedan, 52.365f, 82.165f),
        Province("hamun_lake", R.drawable.hamun_lake, R.drawable.hamun_lake, 230.785f, 234.83f),
        Province("hormozgan", R.drawable.hormozgan, R.drawable.hormozgan, 131.255f, 213.16f),
        Province("ilam", R.drawable.ilam, R.drawable.ilam, 22.185f, 113.965f),
        Province("kerman", R.drawable.kerman, R.drawable.kerman, 160.09f, 154.635f),
        Province("khuzestan", R.drawable.khuzestan, R.drawable.khuzestan, 49.665f, 133.97f),
        Province("kohgeluyeh", R.drawable.kohgeluyeh, R.drawable.kohgeluyeh, 86.67f, 165.25f),
        Province("kermanshah", R.drawable.kermanshah, R.drawable.kermanshah, 16.835f, 90.425f),
        Province("kurdistan", R.drawable.kurdistan, R.drawable.kurdistan, 21.5f, 67.625f),
        Province("lake_urmia", R.drawable.lake_urmia, R.drawable.lake_urmia, 16.465f, 31.38f),
        Province("lorestan", R.drawable.lorestan, R.drawable.lorestan, 40.24f, 107.945f),
        Province("markazi", R.drawable.markazi, R.drawable.markazi, 72.74f, 84.64f),
        Province("mazandaran", R.drawable.mazandaran, R.drawable.mazandaran, 96.21f, 60.685f),
        Province("namak_lake", R.drawable.namak_lake, R.drawable.namak_lake, 118.78f, 102.34f),
        Province(
            "north_khorasan",
            R.drawable.north_khorasan,
            R.drawable.north_khorasan,
            180.525f,
            35.575f
        ),
        Province(
            "persian_gulf_and_oman_sea",
            R.drawable.persian_gulf_and_oman_sea,
            R.drawable.persian_gulf_and_oman_sea,
            49.9f,
            185.085f
        ),
        Province("qazvin", R.drawable.qazvin, R.drawable.qazvin, 70.265f, 62.925f),
        Province("qom", R.drawable.qom, R.drawable.qom, 89.51f, 93.525f),
        Province(
            "razavi_khorasan",
            R.drawable.razavi_khorasan,
            R.drawable.razavi_khorasan,
            187.78f,
            46.045f
        ),
        Province("semnan", R.drawable.semnan, R.drawable.semnan, 119.305f, 54.375f),
        Province(
            "south_khorasan",
            R.drawable.south_khorasan,
            R.drawable.south_khorasan,
            199.57f,
            102.005f
        ),
        Province("tehran", R.drawable.tehran, R.drawable.tehran, 103.15f, 78.365f),
        Province(
            "west_azarbayjan",
            R.drawable.west_azarbayjan,
            R.drawable.west_azarbayjan,
            3.04f,
            1.84f
        ),
        Province("yazd", R.drawable.yazd, R.drawable.yazd, 134.45f, 95.39f),
        Province("zanjan", R.drawable.zanjan, R.drawable.zanjan, 47.825f, 54.965f),
    )

    MapView(provinces = provinces, onProvinceSelected = onProvinceSelected)
}

@Preview
@Composable
fun ShowIranProvinceMapView() {
    val context = LocalContext.current

    IranProvinceMapView {
        Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
    }
}
