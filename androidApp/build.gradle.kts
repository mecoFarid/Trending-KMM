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
    implementation(libs.composeUiTooling)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeFoundation)
    implementation(libs.composeMaterial)
    implementation(libs.composeActivity)

    implementation(libs.viewModelKtx)
    implementation(libs.liveDataKtx)
    implementation(libs.fragmentKtx)
    implementation(libs.appCompat)
    implementation(libs.materialUi)
    implementation(libs.constraintLayout)
    implementation(libs.recyclerview)
    implementation(libs.swipeRefreshLayout)

    implementation(libs.lottie)
    implementation(libs.shimmer)
    implementation(libs.glide)
}