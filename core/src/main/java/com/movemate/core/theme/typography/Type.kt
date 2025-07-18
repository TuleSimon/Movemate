package com.movemate.core.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.movemate.core.R

val rubik = FontFamily(
    Font(R.font.rubik, FontWeight.Normal),
    Font(R.font.rubik_black, FontWeight.Black),
    Font(R.font.rubik_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.rubik_extrabold, FontWeight.ExtraBold),
    Font(R.font.rubik_extrabold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.rubik_italic, FontWeight.Normal),
    Font(R.font.rubik_light, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.rubik_light_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.rubik_semibold, FontWeight.SemiBold),
    Font(R.font.rubik_semibold_italic, FontWeight.Normal,FontStyle.Italic),
)

val baseRubTextStyle = TextStyle(
    fontFamily = rubik,
)

val movemateTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    titleMedium = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.W700,
        fontSize = 25.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    displayMedium = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    displaySmall = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    )