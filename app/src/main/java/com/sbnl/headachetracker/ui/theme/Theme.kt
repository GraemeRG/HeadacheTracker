package com.sbnl.headachetracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PastelBlack,
    primaryVariant = EerieBlack,
    secondary = PastelRed,
    onPrimary = PastelWhite
)

private val LightColorPalette = lightColors(
    primary = VeryLightRed,
    primaryVariant = SoftRed,
    secondary = DesaturatedDarkBlue,
    onPrimary = PastelBlack,
    surface = LightGrayishOrange,
    background = LightGrayishYellow

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun HeadacheTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}