package com.szn.kaam.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = kaamVariant,
    secondary = Teal200,
    onBackground = Color.Black,
    background = Color(0xB9000000),
    onPrimary = Color.White,
    surface = Color.Black,
    )

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = kaamVariant,
    secondary = Teal200,
    onBackground = Color.White,
    background = Color.White,
    onPrimary = Color.Black,
    surface = Color.White,


    /* Other default colors to override
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun KaamTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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