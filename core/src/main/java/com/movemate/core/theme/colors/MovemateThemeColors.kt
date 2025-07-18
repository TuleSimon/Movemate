package com.movemate.core.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient

interface MovemateThemeColors {
    val primary: Color
    val secondary: Color
    val isDark: Boolean
    val textColor:Color
    val cardColor:Color
    val onCardColor:Color
    val bgColor:Color
    val textColorHeader:Color
}
