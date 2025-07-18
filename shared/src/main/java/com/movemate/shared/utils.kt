package com.movemate.shared

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcelable
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.compositionLocalOf
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toFile
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URLConnection
import java.util.Calendar


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

s

fun <T> Map<String, Any>.convertMapToObject(classValue: Class<T>): T? {
    try {
        val gson = Gson()
        val jsonString = gson.toJson(this)
        return gson.fromJson(jsonString, classValue)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }


val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}


