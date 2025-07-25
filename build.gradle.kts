// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.ksp) apply  false
    alias(libs.plugins.google.dagger.hilt.android) apply  false
    alias(libs.plugins.kotlin.serilization) apply false
    alias(libs.plugins.android.library) apply false
    id("com.diffplug.spotless") version "7.1.0" apply false
}
