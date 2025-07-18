// File: build-logic/src/main/kotlin/AndroidLibraryConventionPlugin.kt
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidDataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            // Apply common plugins
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
                apply( "kotlin-parcelize")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            // Android configuration
            extensions.configure<LibraryExtension> {
                configureKotlinAndroidData(this)
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

internal fun Project.configureKotlinAndroidData(
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
        add("implementation", libs.findLibrary("timber").get())

        add("implementation", libs.findBundle("api").get())
        add("implementation", libs.findBundle("android.hilt").get())
        add("implementation", libs.findBundle("android.room").get())
        add("implementation", libs.findBundle("android.work").get())
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())

        add("ksp", libs.findLibrary("androidx.hilt.compiler").get())
        add("ksp", libs.findLibrary("hilt.android.compiler").get())
        add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
        // Room
        add("ksp", libs.findLibrary("androidx.room.compiler").get())
        add("annotationProcessor", libs.findLibrary("androidx.room.compiler").get())

    }
}
