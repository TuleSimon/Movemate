package com.movemate.home.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.movemate.shared.logger.MovemateLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

    fun handleHomeScreenAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.toggleSearchMode -> {
                _homeScreenState.update {
                    it.copy(searchMode = action.searchMode)
                }
            }

            is HomeScreenAction.updateQuery -> {
                _homeScreenState.update {
                    it.copy(
                        query = action.query
                    )
                }
            }
        }
    }

}