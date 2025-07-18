package com.movemate.core.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.movemate.core.theme.colors.MovemateThemeColors

class MovemateTypographyImpl(val colors:MovemateThemeColors) : MovemateTypography {
    private val baseTitleStyle =   TextStyle(
            fontFamily = rubik,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 60.sp,
        color = colors.textColorHeader
        )
    override val titleTextExtraLarge: TextStyle
        get() = baseTitleStyle.copy(
            fontWeight = FontWeight.Bold,
        )
    override val titleTextExtraLargeSemiBold: TextStyle
        get() = baseTitleStyle.copy(
            fontWeight = FontWeight.SemiBold,
        )
    override val titleTextLargeMedium: TextStyle
        get() = baseTitleStyle.copy(
            fontWeight = FontWeight.Medium,
        )
    override val titleTextExtraLargeMedium: TextStyle
        get() = baseTitleStyle.copy(
            fontWeight = FontWeight.Medium,
        )
    override val titleTextLarge: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 48.sp
        )
    override val titleTextLargeSemiBold: TextStyle
        get() =  baseTitleStyle.copy(
            fontWeight = FontWeight.SemiBold)

    override val titleTextMedium: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            color = colors.textColorHeader
        )
    override val titleTextSmall: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp,
            color = colors.textColorHeader
        )
    override val headingTextLarge: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            color = colors.textColorHeader
        )

    override val headingTextMedium: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 21.sp,
            color = colors.textColorHeader
        )
    override val headingTextSmall: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 24.sp,
            color = colors.textColor
        )
    override val bodyTextLarge: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            color = colors.textColor
        )
    override val bodyTextMedium: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            color = colors.textColor
        )
    override val bodyTextMediumAlternate: TextStyle
        get() = bodyTextMedium.copy(fontSize = 15.sp)
    override val bodyTextSmall: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 14.sp,
            color = colors.textColor
        )
    override val bodyTextSmallNormal: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
            color = colors.textColor
        )
    override val bodyTextLargeNormal: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
            color = colors.textColor
        )
    override val bodyTextMediumNormal: TextStyle
        get() = TextStyle(
            fontFamily = rubik,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
            color = colors.textColor
        )
}