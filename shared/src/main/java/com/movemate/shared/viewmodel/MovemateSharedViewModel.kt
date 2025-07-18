package com.movemate.shared.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MoveMateSharedViewModel @Inject constructor() : ViewModel() {

    private val _moveMateAppState = MutableStateFlow(MovemateGlobalAppState())

    val moveMateAppState = _moveMateAppState.onStart {
        if(!_moveMateAppState.value.showBottomNav) {
            handleAction(MoveMateActions.OpenBottomNavigation)
        }
    }


    /**
     * Handles actions related to the global app state.
     *
     * @param action The action to handle.
     */
    fun handleAction(action: MoveMateActions) {
        when (action) {
            MoveMateActions.CloseBottomNavigation -> {
                _moveMateAppState.update {
                    it.copy(showBottomNav = false)
                }
            }

            MoveMateActions.OpenBottomNavigation -> {
                _moveMateAppState.update {
                    it.copy(showBottomNav = true)
                }
            }
        }
    }

}

data class MovemateGlobalAppState(
    val showBottomNav: Boolean = false
)