import src.main.java.toInt

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    namespace = "com.mecofarid.trending.android"
    compileSdk = libs.versions.compileTargetSdk.toInt()
    defaultConfig {
        applicationId = "com.mecofarid.trending.android"
        minSdk = libs.versions.minSdk.toInt()
        targetSdk = libs.versions.compileTargetSdk.toInt()
        versionCode = libs.versions.versionCode.toInt()
        versionName = libs.versions.version.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeFoundation)
    implementation(libs.composeMaterial3)
    implementation(libs.composeActivity)
    implementation(libs.navigation)
    implementation(libs.composeRuntime)
    implementation(libs.composeConstraintLayout)

    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiToolingPreview)

    implementation(libs.coil)
    implementation(libs.lottie)
    implementation(libs.shimmer)
}