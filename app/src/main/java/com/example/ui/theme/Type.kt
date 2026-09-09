package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Roboto Mono Bold font family using Monospace on Android
val RobotoMonoBoldFamily = FontFamily.Monospace

// 31px font size, 40px line height, -3px letter spacing
val HeadingTextStyle =
    TextStyle(
        fontFamily = RobotoMonoBoldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 31.sp,
        lineHeight = 40.sp,
        letterSpacing = (-3).sp,
    )

// 16px font size, 14px line height, -1px letter spacing
val DescriptionTextStyle =
    TextStyle(
        fontFamily = RobotoMonoBoldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 14.sp,
        letterSpacing = (-1).sp,
    )

val NavLabelTextStyle =
    TextStyle(
        fontFamily = RobotoMonoBoldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.5).sp,
    )

val Typography =
    Typography(
        headlineLarge = HeadingTextStyle,
        headlineMedium = HeadingTextStyle,
        titleLarge = HeadingTextStyle,
        titleMedium = HeadingTextStyle,
        bodyLarge = DescriptionTextStyle,
        bodyMedium = DescriptionTextStyle,
        labelMedium = NavLabelTextStyle,
    )
