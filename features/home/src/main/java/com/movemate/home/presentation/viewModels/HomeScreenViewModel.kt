package com.movemate.home.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _homeScreenState = MutableStateFlow(HomeScreenState(showAppBar = false))
    val homeScreenState = _homeScreenState.asStateFlow()


    init {
        viewModelScope.launch {
            delay(500)
            _homeScreenState.update {
                it.copy(showAppBar = true)
            }
        }
    }

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