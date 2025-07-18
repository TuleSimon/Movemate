plugins {
    `kotlin-dsl`
}

group = "com.movemate.buildLogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibraryCompose") {
            id = "movemate.android.library.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryComposeCommon") {
            id = "movemate.android.library.common"
            implementationClass = "AndroidApplicationComposeConventionCommonPlugin"
        }
        register("spotless") {
            id = "movemate.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("androidLibraryData") {
            id = "movemate.android.library.data"
            implementationClass = "AndroidDataPlugin"
        }

    }
}
