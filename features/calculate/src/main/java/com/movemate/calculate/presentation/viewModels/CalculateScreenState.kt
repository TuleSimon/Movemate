package com.movemate.calculate.presentation.viewModels

data class CalculateScreenState(
    val senderLocation: String = "",
    val receiverLocation: String = "",
    val weight: String = "",
    val box: String = "",
    val categories: List<String> = listOf(
        "Document", "Glass", "Liquid", "Food", "Electronic", "Product", "Others"
    )
)