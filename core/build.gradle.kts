plugins {
    id("movemate.android.library.compose")
    alias(libs.plugins.kotlin.compose)
}
android {
    namespace = "com.movemate.core"
}
dependencies {
     implementation(libs.material)
}