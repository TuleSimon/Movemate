package com.movemate.home.presentation.viewModels

sealed interface HomeScreenAction {
    data class toggleSearchMode(val searchMode: Boolean) : HomeScreenAction
    data class updateQuery(val query: String) : HomeScreenAction
}