plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.room)
    alias(libs.plugins.kotlin.serilization)
    alias(libs.plugins.google.dagger.hilt.android)
    id("movemate.spotless")
}

android {
    namespace = "com.simon.movemate"
    compileSdk = 36

    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "123456"
            storeFile = file("movemate.jks")
            storePassword = "123456"
        }

        getByName("debug") {
            keyAlias = "key0"
            keyPassword = "123456"
            storeFile = file("movemate.jks")
            storePassword = "123456"
        }
    }

    defaultConfig {
        applicationId = "com.simon.movemate"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = project.findProperty("minifyEnabled")?.toString()?.toBoolean() ?: true
            isShrinkResources = project.findProperty("minifyEnabled")?.toString()?.toBoolean() ?: true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }

    }

    flavorDimensions += "default"
    productFlavors {
        create("dev") {
            dimension = "default"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
        }
        create("prod") {
            dimension = "default"
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {
    implementation(project(":data"))
    implementation(project(":core"))
    implementation(project(":shared"))
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    coreLibraryDesugaring(libs.android.desugarJdkLibs)

    implementation(libs.bundles.android.lifecycle)
    implementation(libs.bundles.android.testing)
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.android.compose)

    //Hilt
    implementation(libs.bundles.android.hilt)
    annotationProcessor(libs.androidx.hilt.compiler)
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.hilt.android.compiler)

    //Timber
    implementation(libs.bundles.android.compose)
    implementation(libs.bundles.image.loading)
    implementation(libs.timber)

    implementation(libs.androidx.datastore.core)


}