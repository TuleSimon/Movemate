package com.movemate.shared.viewmodel

sealed interface MoveMateActions {

    data object OpenBottomNavigation : MoveMateActions
    data object CloseBottomNavigation : MoveMateActions

}