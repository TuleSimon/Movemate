package com.movemate.core.theme.typography

import androidx.compose.ui.text.TextStyle


interface MovemateTypography {
    /**
     * font size: 50
     * font weight:  bold
     */
    val titleTextExtraLarge:TextStyle
    /**
     * font size: 50
     * font weight:  bold
     */
    val titleTextExtraLargeSemiBold:TextStyle
    /**
     * font size: 50
     * font weight:  bold
     */
    val titleTextExtraLargeMedium:TextStyle
    /**
     * font size: 40
     * font weight:  bold
     */
    val titleTextLarge:TextStyle
    /**
     * font size: 40
     * font weight:  bold
     */
    val titleTextLargeSemiBold:TextStyle
    /**
     * font size: 40
     * font weight:  bold
     */
    val titleTextLargeMedium:TextStyle
    /**
     * font size: 30
     * font weight:  bold
     */
    val titleTextMedium:TextStyle
    /**
     * font size: 24
     * font weight:  bold
     */
    val titleTextSmall:TextStyle
    /**
     * font size: 24
     * font weight:  bold
     */
    val headingTextLarge:TextStyle

    /**
     * font size: 20
     * font weight: semi bold
     */
    val headingTextMedium:TextStyle
    /**
     * font size: 18
     * font weight: semi bold
     */
    val headingTextSmall:TextStyle
    /**
     * font size: 16
     * font weight: medium
     */
    val bodyTextLarge:TextStyle
    /**
     * font size: 14
     * font weight: medium
     */
    val bodyTextMedium:TextStyle
    /**
     * font size: 15
     * font weight: medium
     */
    val bodyTextMediumAlternate:TextStyle
    /**
     * font size: 12
     * font weight: medium
     */
    val bodyTextSmall:TextStyle
    /**
     * font size: 12
     * font weight: normal
     */
    val bodyTextSmallNormal:TextStyle
    /**
     * font size: 14
     * font weight: normal
     */
    val bodyTextMediumNormal:TextStyle
    /**
     * font size: 16
     * font weight: normal
     */
    val bodyTextLargeNormal:TextStyle
}