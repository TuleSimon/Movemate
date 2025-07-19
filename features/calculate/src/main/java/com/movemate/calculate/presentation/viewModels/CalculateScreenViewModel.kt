package com.movemate.calculate.presentation.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CalculateScreenViewModel @Inject constructor() : ViewModel() {

    private val _calculateScreenState = MutableStateFlow(CalculateScreenState())
    val calculateScreenState: StateFlow<CalculateScreenState> = _calculateScreenState.asStateFlow()

    fun handleHomeScreenAction(action: CalculateScreenAction) {
        when (action) {
            is CalculateScreenAction.UpdateSenderLocation -> {
                _calculateScreenState.update { it.copy(senderLocation = action.input) }
            }

            is CalculateScreenAction.UpdateReceiverLocation -> {
                _calculateScreenState.update { it.copy(receiverLocation = action.input) }
            }

            is CalculateScreenAction.UpdateWeight -> {
                _calculateScreenState.update { it.copy(weight = action.input) }
            }

            is CalculateScreenAction.UpdateBox -> {
                _calculateScreenState.update { it.copy(box = action.input) }
            }
        }
    }
}
