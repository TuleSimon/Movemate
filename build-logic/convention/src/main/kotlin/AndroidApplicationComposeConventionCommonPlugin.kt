// File: build-logic/src/main/kotlin/AndroidLibraryConventionPlugin.kt
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApplicationComposeConventionCommonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply common plugins
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
                apply( "kotlin-parcelize")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            // Android configuration
            extensions.configure<LibraryExtension> {
                configureKotlinAndroidCommon(this)
                defaultConfig.targetSdk = 35
                defaultConfig {
                    minSdk = 24
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
            }
        }
    }
}

internal fun Project.configureKotlinAndroidCommon(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 35

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }


        buildFeatures {
            compose = true
            buildConfig = true
        }
        // Set Kotlin JVM target through KotlinCompile task
        tasks.withType<KotlinCompile>().configureEach {

            kotlinOptions {
                jvmTarget = "17"  // Ensure this matches the Java version
            }
        }


        flavorDimensions.add("default")
        productFlavors {
            create("dev") {
                dimension = "default"
            }
            create("prod") {
                dimension = "default"
            }
        }
    }

    // Version catalog
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    // Dependencies
    dependencies {
        add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
        add("androidTestImplementation", platform(libs.findLibrary("androidx.compose.bom").get()))
        add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
        add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
        add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
        add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
        add("implementation", libs.findBundle("android.lifecycle").get())
        add("implementation", libs.findBundle("image.loading").get())
        add("implementation", libs.findBundle("android.compose").get())
        add("implementation", libs.findBundle("converters").get())
        add("implementation", libs.findBundle("android.datastore").get())
        add("implementation", libs.findBundle("android.testing").get())
        add("implementation", libs.findBundle("android.core").get())
        add("implementation", libs.findBundle("android.hilt").get())
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
        add("implementation", project(":core"))
        add("implementation", project(":data"))
        add("ksp", libs.findLibrary("androidx.hilt.compiler").get())
        add("ksp", libs.findLibrary("hilt.android.compiler").get())
    }
}
