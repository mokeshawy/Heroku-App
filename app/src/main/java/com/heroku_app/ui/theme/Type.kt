package com.heroku_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.heroku_app.R

val cairoFamily = FontFamily(
    Font(R.font.cairo_regular, FontWeight.Normal),
    Font(R.font.cairo_medium, FontWeight.Medium),
    Font(R.font.cairo_semibold, FontWeight.SemiBold),
    Font(R.font.cairo_bold, FontWeight.Bold)
)

private val default = Typography()


val AppTypography = default.copy(
    displayLarge = default.displayLarge.copy(
        fontFamily = cairoFamily,
    ),
    displayMedium = default.displayMedium.copy(
        fontFamily = cairoFamily,
    ),
    displaySmall = default.displaySmall.copy(
        fontFamily = cairoFamily,
    ),
    headlineLarge = default.headlineLarge.copy(
        fontFamily = cairoFamily,
    ),
    headlineMedium = default.headlineMedium.copy(
        fontFamily = cairoFamily,
    ),
    headlineSmall = default.headlineSmall.copy(
        fontFamily = cairoFamily,
    ),
    titleLarge = default.titleLarge.copy(
        fontFamily = cairoFamily,
    ),
    titleMedium = default.titleMedium.copy(
        fontFamily = cairoFamily,
    ),
    titleSmall = default.titleSmall.copy(
        fontFamily = cairoFamily,
    ),
    bodyLarge = default.bodyLarge.copy(
        fontFamily = cairoFamily,
    ),
    bodyMedium = default.bodyMedium.copy(
        fontFamily = cairoFamily,
    ),
    bodySmall = default.bodySmall.copy(
        fontFamily = cairoFamily,
    ),
    labelLarge = default.labelLarge.copy(
        fontFamily = cairoFamily,
    ),
    labelMedium = default.labelMedium.copy(
        fontFamily = cairoFamily,
    ),
    labelSmall = default.labelSmall.copy(
        fontFamily = cairoFamily,
    ),
)


val TextStyle.regular: TextStyle
    get() = this.copy(fontWeight = FontWeight.Normal, lineHeight = fontSize)

val TextStyle.medium: TextStyle
    get() = this.copy(fontWeight = FontWeight.Medium, lineHeight = fontSize)

val TextStyle.semiBold: TextStyle
    get() = this.copy(fontWeight = FontWeight.SemiBold, lineHeight = fontSize)

val TextStyle.bold: TextStyle
    get() = this.copy(fontWeight = FontWeight.Bold, lineHeight = fontSize)