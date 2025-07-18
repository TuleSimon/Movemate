package com.movemate.core.theme.responsiveness

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Scaling class to scale sizes based on the screen width and height
 * @param screenWidth The width of the screen
 * @param screenHeight The height of the screen
 * @param baseWidth The base width to scale from
 * @param baseHeight The base height to scale from
 */
data class ResponsivenessUtils(
    val screenWidth: Float,
    val screenHeight: Float,
    val baseWidth: Int = 360,
    val baseHeight: Int = 800
) {
    /**
     * The short dimension of the screen
     */
    val shortDimension = minOf(screenWidth, screenHeight)

    /**
     * The long dimension of the screen
     */
    val longDimension = maxOf(screenWidth, screenHeight)

    /**
     * Scale a size based on the screen width (the short dimension)
     */
    fun scale(size: Float): Float {
        return (shortDimension / baseWidth) * size
    }

    /**
     * Scale a size based on the screen height (the long dimension)
     */
    fun verticalScale(size: Float): Float {
        return (longDimension / baseHeight) * size
    }

    /**
     * Scale a size based on the screen width (the short dimension) with a factor
     */
    fun moderateScale(size: Float, factor: Float = 0.5f): Float {
        return size + (scale(size) - size) * factor
    }

    /**
     * Scale a size based on the screen height (the long dimension) with a factor
     */
    fun moderateVerticalScale(size: Float, factor: Float = 0.5f): Float {
        return size + (verticalScale(size) - size) * factor
    }
}

val LocalScaling = compositionLocalOf<ResponsivenessUtils> {
    error("No Scaling provided")
}

/**
 * Scale a number to the current screen width
 */
val Number.w: Float
    @ReadOnlyComposable
    @Composable
    get() = LocalScaling.current.scale(this.toFloat())

/**
 * Scale a number to the current screen width and convert it to Dp
 */
val Number.wdp: Dp
    @ReadOnlyComposable
    @Composable
    get() = LocalScaling.current.scale(this.toFloat()).dp

/**
 * Scale a number to the current screen width and convert it to TextUnit
 */
val Number.wsp: TextUnit
    @ReadOnlyComposable
    @Composable
    get() = LocalScaling.current.scale(this.toFloat()).sp

/**
 * Scale a number to the current screen height
 */
val Number.h: Float
    @ReadOnlyComposable
    @Composable
    get() = LocalScaling.current.verticalScale(this.toFloat())

/**
 * Scale a number to the current screen height and convert it to Dp
 */
val Number.hdp: Dp
    @ReadOnlyComposable
    @Composable
    get() = LocalScaling.current.verticalScale(this.toFloat()).dp

/**
 * Scale a number to the current screen height and convert it to TextUnit
 */
val Number.hsp: TextUnit
    @ReadOnlyComposable
    @Composable
    get() = LocalScaling.current.verticalScale(this.toFloat()).sp