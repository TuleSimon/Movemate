plugins {
    id("movemate.android.library.common")
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization")
}
android{
    namespace =  "com.movemate.home"
}
