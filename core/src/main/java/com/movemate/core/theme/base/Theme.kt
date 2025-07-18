package com.movemate.core.theme.base

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import com.movemate.core.theme.colors.MovemateColors
import com.movemate.core.theme.colors.MovemateDarkThemeColors
import com.movemate.core.theme.colors.MovemateLightThemeLight
import com.movemate.core.theme.colors.MovemateThemeColors
import com.movemate.core.theme.responsiveness.LocalScaling
import com.movemate.core.theme.responsiveness.ResponsivenessUtils
import com.movemate.core.theme.typography.MovemateTypography
import com.movemate.core.theme.typography.MovemateTypographyImpl
import com.movemate.core.theme.typography.movemateTypography


private val MovemateDarkColorScheme = darkColorScheme(
    background = MovemateColors.background,
    primary = MovemateColors.primary

)

// Define the light color scheme
private val MovemateLightColorScheme = lightColorScheme(
    background = MovemateColors.background,
    primary = MovemateColors.primary
)

@Composable
fun MovemateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val configuration = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current
    val scaling = remember(configuration) {
        with(density) {
            ResponsivenessUtils(
                screenWidth = configuration.width.toDp().value,
                screenHeight = configuration.height.toDp().value,
            )
        }
    }
    val colorScheme = when {
        darkTheme -> MovemateDarkColorScheme
        else -> MovemateLightColorScheme
    }
    val movemateColor = when {
        darkTheme -> MovemateDarkThemeColors
        else -> MovemateLightThemeLight
    }

    CompositionLocalProvider(
        LocalScaling provides scaling,
        LocalColorsProvider provides movemateColor,
        LocalTypograhpyProvider provides MovemateTypographyImpl(movemateColor),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = movemateTypography,
            content = content,
        )
    }
}

val MaterialTheme.MovemateColors: MovemateThemeColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColorsProvider.current


val MaterialTheme.MovemateTypes: MovemateTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypograhpyProvider.current


val LocalColorsProvider = compositionLocalOf<MovemateThemeColors> {
    error("No LocalColorsProvider Provided")
}

val LocalTypograhpyProvider = compositionLocalOf<MovemateTypography> {
    error("No LocalMovemateTypesProvider Provided")
}

