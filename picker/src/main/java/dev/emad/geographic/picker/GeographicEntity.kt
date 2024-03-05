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
    GeographicEntity("alborz", R.drawable.alborz, R.drawable.alborz_selected, 94.325f, 72.185f),
    GeographicEntity("ardabil", R.drawable.ardabil, R.drawable.ardabil_selected, 47.82f, 6.455f),
    GeographicEntity(
        "balouchestan", R.drawable.balouchestan, R.drawable.balouchestan_selected, 233.245f, 163.88f
    ),
    GeographicEntity("bushehr", R.drawable.bushehr, R.drawable.bushehr_selected, 88.67f, 187.915f),
    GeographicEntity(
        "caspian_sea",
        R.drawable.caspian_sea,
        R.drawable.caspian_sea_selected,
        75.055f,
        12.41f
    ),
    GeographicEntity("charmahal", R.drawable.charmahal, R.drawable.charmahal_selected, 84.85f, 142.66f),
    GeographicEntity(
        "east_azarbayjan",
        R.drawable.east_azarbayjan,
        R.drawable.east_azarbayjan_selected,
        18.21f,
        15.995f
    ),
    GeographicEntity("esfahan", R.drawable.esfahan, R.drawable.esfahan_selected, 80.775f, 107.94f),
    GeographicEntity("fars", R.drawable.fars, R.drawable.fars_selected, 97.265f, 160.99f),
    GeographicEntity("gilan", R.drawable.gilan, R.drawable.gilan_selected, 69.465f, 31.74f),
    GeographicEntity("golestan", R.drawable.golestan, R.drawable.golestan_selected, 149.395f, 39.185f),
    GeographicEntity("hamedan", R.drawable.hamedan, R.drawable.hamedan_selected, 52.365f, 82.165f),
    GeographicEntity(
        "hamun_lake",
        R.drawable.hamun_lake,
        R.drawable.hamun_lake_selected,
        230.785f,
        234.83f
    ),
    GeographicEntity("hormozgan", R.drawable.hormozgan, R.drawable.hormozgan_selected, 131.255f, 213.16f),
    GeographicEntity("ilam", R.drawable.ilam, R.drawable.ilam_selected, 22.185f, 113.965f),
    GeographicEntity("kerman", R.drawable.kerman, R.drawable.kerman_selected, 160.09f, 154.635f),
    GeographicEntity("khuzestan", R.drawable.khuzestan, R.drawable.khuzestan_selected, 49.665f, 133.97f),
    GeographicEntity("kohgeluyeh", R.drawable.kohgeluyeh, R.drawable.kohgeluyeh_selected, 86.67f, 165.25f),
    GeographicEntity("kermanshah", R.drawable.kermanshah, R.drawable.kermanshah_selected, 16.835f, 90.425f),
    GeographicEntity("kurdistan", R.drawable.kurdistan, R.drawable.kurdistan_selected, 21.5f, 67.625f),
    GeographicEntity("lake_urmia", R.drawable.lake_urmia, R.drawable.lake_urmia_selected, 16.465f, 31.38f),
    GeographicEntity("lorestan", R.drawable.lorestan, R.drawable.lorestan_selected, 40.24f, 107.945f),
    GeographicEntity("markazi", R.drawable.markazi, R.drawable.markazi_selected, 72.74f, 84.64f),
    GeographicEntity("mazandaran", R.drawable.mazandaran, R.drawable.mazandaran_selected, 96.21f, 60.685f),
    GeographicEntity("namak_lake", R.drawable.namak_lake, R.drawable.namak_lake_selected, 118.78f, 102.34f),
    GeographicEntity(
        "north_khorasan",
        R.drawable.north_khorasan,
        R.drawable.north_khorasan_selected,
        180.525f,
        35.575f
    ),
    GeographicEntity(
        "persian_gulf_and_oman_sea",
        R.drawable.persian_gulf,
        R.drawable.persian_gulf_selected,
        49.9f,
        185.085f
    ),
    GeographicEntity("qazvin", R.drawable.qazvin, R.drawable.qazvin_selected, 70.265f, 62.925f),
    GeographicEntity("qom", R.drawable.qom, R.drawable.qom_selected, 89.51f, 93.525f),
    GeographicEntity(
        "razavi_khorasan",
        R.drawable.razavi_khorasan,
        R.drawable.razavi_khorasan_selected,
        187.78f,
        46.045f
    ),
    GeographicEntity("semnan", R.drawable.semnan, R.drawable.semnan_selected, 119.305f, 54.375f),
    GeographicEntity(
        "south_khorasan",
        R.drawable.south_khorasan,
        R.drawable.south_khorasan_selected,
        199.57f,
        102.005f
    ),
    GeographicEntity("tehran", R.drawable.tehran, R.drawable.tehran_selected, 103.15f, 78.365f),
    GeographicEntity(
        "west_azarbayjan",
        R.drawable.west_azarbayjan,
        R.drawable.west_azarbayjan_selected,
        3.04f,
        1.84f
    ),
    GeographicEntity("yazd", R.drawable.yazd, R.drawable.yazd_selected, 134.45f, 95.39f),
    GeographicEntity("zanjan", R.drawable.zanjan, R.drawable.zanjan_selected, 47.825f, 54.965f),
)
