package com.movemate.calculate.presentation.viewModels

sealed interface CalculateScreenAction {
    data class UpdateSenderLocation(val input: String) : CalculateScreenAction
    data class UpdateSelectedCategory(val input: String) : CalculateScreenAction
    data class UpdateReceiverLocation(val input: String) : CalculateScreenAction
    data class UpdateWeight(val input: String) : CalculateScreenAction
    data class UpdateBox(val input: String) : CalculateScreenAction
    data object OnCalculate : CalculateScreenAction
    data object OnBackPress : CalculateScreenAction
}
