package com.movemate.calculate.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.movemate.shared.logger.MovemateLogger
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

            is CalculateScreenAction.UpdateSelectedCategory -> {
                MovemateLogger.d("Updating Category ${action.input}")
                _calculateScreenState.update { it.copy(selectedCategory = action.input) }
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

            CalculateScreenAction.OnCalculate -> {
                _calculateScreenState.update { it.copy(showSuccess = true) }
            }

            CalculateScreenAction.OnBackPress -> {
                _calculateScreenState.update { it.copy(showSuccess = false) }
            }
        }
    }
}
