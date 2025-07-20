package com.movemate.shipment.presentation.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShipmentScreenViewModel @Inject constructor() : ViewModel() {

    private val _shipmentState = MutableStateFlow(ShipmentState())
    val shipmentState = _shipmentState.asStateFlow()


    fun updateSelectedShipmentTab(id: Int) {
        _shipmentState.value = _shipmentState.value.copy(
            selectedTabId = id
        )
    }

}
