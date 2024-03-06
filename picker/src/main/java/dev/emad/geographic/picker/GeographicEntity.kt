package dev.emad.geographic.picker

import androidx.annotation.DrawableRes

data class GeographicEntity(
    val name: String,
    @DrawableRes val drawableId: Int,
    @DrawableRes val selectedDrawableId: Int,
    val x: Float,
    val y: Float,
)

val geographicEntities = listOf(
    GeographicEntity("Alborz", R.drawable.alborz, R.drawable.alborz_selected, 94.325f, 72.185f),
    GeographicEntity("Ardabil", R.drawable.ardabil, R.drawable.ardabil_selected, 47.82f, 6.455f),
    GeographicEntity(
        "Sistan and Baluchestan",
        R.drawable.balouchestan,
        R.drawable.balouchestan_selected,
        233.245f,
        163.88f
    ),
    GeographicEntity("Bushehr", R.drawable.bushehr, R.drawable.bushehr_selected, 88.67f, 187.915f),
    GeographicEntity(
        "Caspian Sea",
        R.drawable.caspian_sea,
        R.drawable.caspian_sea_selected,
        75.055f,
        12.41f
    ),
    GeographicEntity(
        "Chaharmahal and Bakhtiari",
        R.drawable.chaharmahal,
        R.drawable.chaharmahal_selected,
        84.85f,
        142.66f
    ),
    GeographicEntity(
        "East Azerbaijan",
        R.drawable.east_azarbayjan,
        R.drawable.east_azarbayjan_selected,
        18.21f,
        15.995f
    ),
    GeographicEntity("Esfahan", R.drawable.esfahan, R.drawable.esfahan_selected, 80.775f, 107.94f),
    GeographicEntity("Fars", R.drawable.fars, R.drawable.fars_selected, 97.265f, 160.99f),
    GeographicEntity("Gilan", R.drawable.gilan, R.drawable.gilan_selected, 69.465f, 31.74f),
    GeographicEntity(
        "Golestan",
        R.drawable.golestan,
        R.drawable.golestan_selected,
        149.395f,
        39.185f
    ),
    GeographicEntity("Hamedan", R.drawable.hamedan, R.drawable.hamedan_selected, 52.365f, 82.165f),
    GeographicEntity(
        "Hamun Lake",
        R.drawable.hamun_lake,
        R.drawable.hamun_lake_selected,
        230.785f,
        234.83f
    ),
    GeographicEntity(
        "Hormozgan",
        R.drawable.hormozgan,
        R.drawable.hormozgan_selected,
        131.255f,
        213.16f
    ),
    GeographicEntity("Ilam", R.drawable.ilam, R.drawable.ilam_selected, 22.185f, 113.965f),
    GeographicEntity("Kerman", R.drawable.kerman, R.drawable.kerman_selected, 160.09f, 154.635f),
    GeographicEntity(
        "Khuzestan",
        R.drawable.khuzestan,
        R.drawable.khuzestan_selected,
        49.665f,
        133.97f
    ),
    GeographicEntity(
        "Kohgiluyeh and Boyer-Ahmad",
        R.drawable.kohgeluyeh,
        R.drawable.kohgeluyeh_selected,
        86.67f,
        165.25f
    ),
    GeographicEntity(
        "Kermanshah",
        R.drawable.kermanshah,
        R.drawable.kermanshah_selected,
        16.835f,
        90.425f
    ),
    GeographicEntity(
        "Kurdistan",
        R.drawable.kurdistan,
        R.drawable.kurdistan_selected,
        21.5f,
        67.625f
    ),
    GeographicEntity(
        "Lake Urmia",
        R.drawable.lake_urmia,
        R.drawable.lake_urmia_selected,
        16.465f,
        31.38f
    ),
    GeographicEntity(
        "Lorestan",
        R.drawable.lorestan,
        R.drawable.lorestan_selected,
        40.24f,
        107.945f
    ),
    GeographicEntity("Markazi", R.drawable.markazi, R.drawable.markazi_selected, 72.74f, 84.64f),
    GeographicEntity(
        "Mazandaran",
        R.drawable.mazandaran,
        R.drawable.mazandaran_selected,
        96.21f,
        60.685f
    ),
    GeographicEntity(
        "namak_lake",
        R.drawable.namak_lake,
        R.drawable.namak_lake_selected,
        118.78f,
        102.34f
    ),
    GeographicEntity(
        "Namak Lake",
        R.drawable.north_khorasan,
        R.drawable.north_khorasan_selected,
        180.525f,
        35.575f
    ),
    GeographicEntity(
        "Persian Gulf and Oman Sea",
        R.drawable.persian_gulf,
        R.drawable.persian_gulf_selected,
        49.9f,
        185.085f
    ),
    GeographicEntity("Qazvin", R.drawable.qazvin, R.drawable.qazvin_selected, 70.265f, 62.925f),
    GeographicEntity("Qom", R.drawable.qom, R.drawable.qom_selected, 89.51f, 93.525f),
    GeographicEntity(
        "Razavi Khorasan",
        R.drawable.razavi_khorasan,
        R.drawable.razavi_khorasan_selected,
        187.78f,
        46.045f
    ),
    GeographicEntity("Semnan", R.drawable.semnan, R.drawable.semnan_selected, 119.305f, 54.375f),
    GeographicEntity(
        "South Khorasan",
        R.drawable.south_khorasan,
        R.drawable.south_khorasan_selected,
        199.57f,
        102.005f
    ),
    GeographicEntity("Tehran", R.drawable.tehran, R.drawable.tehran_selected, 103.15f, 78.365f),
    GeographicEntity(
        "West Azerbaijan",
        R.drawable.west_azarbayjan,
        R.drawable.west_azarbayjan_selected,
        3.04f,
        1.84f
    ),
    GeographicEntity("Yazd", R.drawable.yazd, R.drawable.yazd_selected, 134.45f, 95.39f),
    GeographicEntity("Zanjan", R.drawable.zanjan, R.drawable.zanjan_selected, 47.825f, 54.965f),
)
