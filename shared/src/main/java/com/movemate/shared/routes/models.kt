package com.movemate.shared.routes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class TopLevelRoute<T : Any>(
    @StringRes val name: Int,
    val route: T,
    @DrawableRes val icon: Int
)

@Serializable
object CalculateRoute

@Serializable
object HomeRoute

@Serializable
object ShipmentRoute

@Serializable
object ProfileRoute