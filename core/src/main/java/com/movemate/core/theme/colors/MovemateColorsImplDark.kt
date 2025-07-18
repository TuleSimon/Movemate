package com.movemate.core.theme.colors

import androidx.compose.ui.graphics.Color

object MovemateDarkThemeColors : MovemateThemeColors {

    override val isDark: Boolean
        get() = true

    override val cardColor: Color
        get() = MovemateColors.white

    override val bgColor: Color
        get() = MovemateColors.background

    override val primary: Color
        get() = MovemateColors.primary

    override val textColor: Color
        get() = MovemateColors.textColor

    override val textColorHeader: Color
        get() = MovemateColors.textColorHeader

}


