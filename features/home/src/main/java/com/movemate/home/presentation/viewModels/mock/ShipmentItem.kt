package com.movemate.home.presentation.viewModels.mock

import java.util.UUID


data class ShipmentItem(
    val id:String = UUID.randomUUID().toString(),
    val title: String,
    val trackingNumber: String,
    val route: String
)


val mockShipmentData: List<ShipmentItem> = listOf(
    ShipmentItem(
        title = "Macbook pro M2",
        trackingNumber = "#NE43857340857904",
        route = "Paris → Morocco"
    ),
    ShipmentItem(
        title = "Summer linen jacket",
        trackingNumber = "#NEJ20089934122231",
        route = "Barcelona → Paris"
    ),
    ShipmentItem(
        title = "Tapered-fit jeans AW",
        trackingNumber = "#NEJ35870264978659",
        route = "Colombia → Paris"
    ),
    ShipmentItem(
        title = "Slim fit jeans AW",
        trackingNumber = "#NEJ35870264978652",
        route = "Bogota → Dhaka"
    ),
    ShipmentItem(
        title = "Office setup desk",
        trackingNumber = "#NEJ23481570754963",
        route = "France → German"
    ),
    ShipmentItem(
        title = "Designer coffee table",
        trackingNumber = "#NEJ78901234567890",
        route = "Italy → New York"
    ),
    ShipmentItem(
        title = "Ergonomic office chair",
        trackingNumber = "#NEJ11223344556677",
        route = "China → London"
    ),
    ShipmentItem(
        title = "Wireless headphones Pro",
        trackingNumber = "#NEJ99887766554433",
        route = "Japan → Sydney"
    )
)
