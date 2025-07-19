package com.movemate.core.utils

import android.graphics.BlurMaskFilter
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.shadowCustom(
    color: Color = Color.Black.copy(0.08f),
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 20.dp,
    shapeRadius: Dp = 30.dp,
    spread: Dp = 5.dp // New spread parameter
) = composed {
    val paint: Paint = remember { Paint() }
    val density = LocalDensity.current // Get LocalDensity once

    val blurRadiusPx = with(density) { blurRadius.toPx() } // Use with(density) for Dp.toPx()
    val spreadPx = with(density) { spread.toPx() } // Convert spread to pixels

    val maskFilter = remember(blurRadiusPx) { // Remember maskFilter based on blurRadiusPx
        BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
    }

    drawBehind {
        drawIntoCanvas { canvas ->
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius.value != 0f) { // Check blurRadius.value as Float
                frameworkPaint.maskFilter = maskFilter
            } else {
                frameworkPaint.maskFilter = null // Clear mask filter if blurRadius is 0
            }
            frameworkPaint.color = color.toArgb()

            // Calculate bounds considering spread
            val leftPixel = offsetX.toPx() - spreadPx
            val topPixel = offsetY.toPx() - spreadPx
            val rightPixel = size.width + leftPixel + (spreadPx * 2) // Add spread on both sides
            val bottomPixel = size.height + topPixel + (spreadPx * 2) // Add spread on both sides

            if (shapeRadius.value > 0f) { // Check shapeRadius.value as Float
                val radiusPx = with(density) { shapeRadius.toPx() }
                canvas.drawRoundRect(
                    left = leftPixel,
                    top = topPixel,
                    right = rightPixel,
                    bottom = bottomPixel,
                    radiusX = radiusPx,
                    radiusY = radiusPx,
                    paint = paint,
                )
            } else {
                canvas.drawRect(
                    left = leftPixel,
                    top = topPixel,
                    right = rightPixel,
                    bottom = bottomPixel,
                    paint = paint,
                )
            }
        }
    }
}
