package dev.emad.province.picker

import androidx.annotation.DrawableRes

data class Province(
    val name: String,
    @DrawableRes val drawableId: Int,
    @DrawableRes val selectedDrawableId: Int,
    val x: Float, // x-coordinate for placement
    val y: Float,  // y-coordinate for placement
)