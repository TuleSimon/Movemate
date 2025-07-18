package com.movemate.core.theme.colors

import androidx.compose.ui.graphics.Color

object MovemateLightThemeLight : MovemateThemeColors {

    override val cardColor: Color
        get() = MovemateColors.white

    override val bgColor: Color
        get() = MovemateColors.background

    override val isDark: Boolean
        get() = false

    override val textColor: Color
        get() = MovemateColors.textColor

    override val primary: Color
        get() = MovemateColors.primary

    override val textColorHeader: Color
        get() = MovemateColors.textColorHeader

}
