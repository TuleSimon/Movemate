package com.movemate.shared

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
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

@Composable
fun getContext(): Context {
    return LocalContext.current
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Composable
fun ChangeNavigationBarColors(
    lightStatusBarColor: Color, darkStatusBarColor: Color,
    navigationStatusBarColor: Color,
    navigationDarkStatusBarColor: Color,
    shouldReset: Boolean = false,
    isDarkk: Boolean? = true,
) {

    val isDark = isDarkk ?: true
    val bg = MaterialTheme.colorScheme.background
    val context = getContext() as ComponentActivity

    SideEffect {
        context.enableEdgeToEdge(
            statusBarStyle = if (isDark) {
                SystemBarStyle.dark(darkStatusBarColor.toArgb())
            } else {
                SystemBarStyle.light(lightStatusBarColor.toArgb(), darkStatusBarColor.toArgb())
            },
            navigationBarStyle = if (isDark) {
                SystemBarStyle.dark(navigationStatusBarColor.toArgb())
            } else {
                SystemBarStyle.light(
                    navigationStatusBarColor.toArgb(),
                    navigationDarkStatusBarColor.toArgb()
                )
            },
        )
    }
    DisposableEffect(Unit) {
        // Apply edge-to-edge with custom system bar colors

        onDispose {
            if (shouldReset) {
                // Apply edge-to-edge with custom system bar colors
                context.enableEdgeToEdge(
                    statusBarStyle = if (isDark) {
                        SystemBarStyle.dark(bg.toArgb())
                    } else {
                        SystemBarStyle.light(bg.toArgb(), bg.toArgb())
                    },
                    navigationBarStyle = if (isDark) {
                        SystemBarStyle.dark(bg.toArgb())
                    } else {
                        SystemBarStyle.light(bg.toArgb(), bg.toArgb())
                    },
                )
            }
        }
    }

}


@Composable
inline fun <reified T : ViewModel> getSharedViewModel(): T {
    val context = getContext()
    val activity = context.getActivity()
    return activity?.let {
        hiltViewModel<T>(
            viewModelStoreOwner = activity,
        )
    } ?: hiltViewModel()
}


@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }


val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}


