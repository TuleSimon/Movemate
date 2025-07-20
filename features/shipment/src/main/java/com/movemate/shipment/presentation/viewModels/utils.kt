package com.movemate.shipment.presentation.viewModels

import androidx.compose.ui.graphics.Color
import com.movemate.core.theme.colors.MovemateColors
import com.movemate.shipment.R


data class ShipmentState(
    val selectedTabId:Int = 0
)

data class ShipmentStatus(
    val status: ShipmentStatusLabel,
    val message: String,
    val details: String,
    val amount: String,
    val date: String,
)

enum class ShipmentStatusLabel(
    val id: Int,
    val title: String,
    val tag: String,
    val icon: Int,
    val color: Color,
) {
    ALL(0, "All", "all", R.drawable.ic_add, Color.Magenta),
    COMPLETED(1, "Completed", "completed", R.drawable.ic_check, MovemateColors.green),
    IN_PROGRESS(3, "In-progress", "in-progress", R.drawable.ic_inprogress, MovemateColors.green),
    PENDING_ORDER(2, "Pending order", "pending", R.drawable.ic_pending, MovemateColors.secondary),
    LOADING(4, "Loading", "loading", R.drawable.ic_loading, MovemateColors.blue),
    CANCELLED(5, "Cancelled", "cancelled", R.drawable.ic_cancel, Color.Red),
}


val listOfShipmentStatus = listOf(
    ShipmentStatus(
        status = ShipmentStatusLabel.IN_PROGRESS,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023",
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.PENDING_ORDER,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.IN_PROGRESS,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.PENDING_ORDER,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.COMPLETED,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.COMPLETED,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.COMPLETED,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.COMPLETED,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.PENDING_ORDER,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.CANCELLED,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.LOADING,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.PENDING_ORDER,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
    ShipmentStatus(
        status = ShipmentStatusLabel.COMPLETED,
        message = "Arriving today!",
        details = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today",
        amount = "$1400 USD",
        date = "Sep 20, 2023"
    ),
)