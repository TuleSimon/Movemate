package com.movemate.shared

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.compositionLocalOf
import kotlinx.serialization.json.Json


fun String?.getOrEmpty(): String {
    return this ?: ""
}

fun String.copyToClipboard(
    context: Context,
    label: String,
) {
    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText(label, this)
    clipboard?.setPrimaryClip(clip)
}




@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }


val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}


