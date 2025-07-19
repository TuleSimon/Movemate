package com.movemate.home.presentation.viewModels

data class HomeScreenState(
    val searchMode: Boolean = false,
    val showAppBar: Boolean = true,
    val query: String = "",

)