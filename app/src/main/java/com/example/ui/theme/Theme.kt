package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme =
    lightColorScheme(
        primary = SiteTextPrimary,
        onPrimary = Color.White,
        background = SiteBackground,
        surface = SiteBackground,
        onBackground = SiteTextPrimary,
        onSurface = SiteTextPrimary,
        surfaceVariant = SiteCardLight,
        onSurfaceVariant = SiteTextPrimary,
    )

private val DarkColorScheme =
    darkColorScheme(
        primary = Color.White,
        onPrimary = Color.Black,
        background = Color(0xFF121212),
        surface = Color(0xFF1E1E1E),
        onBackground = Color.White,
        onSurface = Color.White,
        surfaceVariant = Color(0xFF2C2C2C),
        onSurfaceVariant = Color.White,
    )

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Keep dynamicColor false to maintain the developer-focused image specification accurately
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content,
  )
}
