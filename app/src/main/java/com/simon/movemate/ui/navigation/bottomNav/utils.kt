package com.simon.movemate.ui.navigation.bottomNav

import com.movemate.shared.routes.CalculateRoute
import com.movemate.shared.routes.HomeRoute
import com.movemate.shared.routes.ProfileRoute
import com.movemate.shared.routes.ShipmentRoute
import com.movemate.shared.routes.TopLevelRoute
import com.simon.movemate.R

val BOTTOM_MENUS = listOf(
    TopLevelRoute(
        (R.string.home),
        HomeRoute,
        R.drawable.icon_bottom_home,
    ),
    TopLevelRoute((R.string.calculate), CalculateRoute, R.drawable.icon_bottom_calculate),
    TopLevelRoute((R.string.shipment), ShipmentRoute, R.drawable.icon_bottom_shipment),
    TopLevelRoute((R.string.profile), ProfileRoute, R.drawable.icon_bottom_profile),
)