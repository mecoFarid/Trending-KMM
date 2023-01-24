import src.main.java.toInt

plugins {
    id("com.android.application")
    kotlin("android")
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
    debugImplementation(project(":sharedMock"))
    implementation(libs.composeUi)
    implementation(libs.composeUiTooling)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeFoundation)
    implementation(libs.composeMaterial)
    implementation(libs.composeActivity)
    implementation(libs.viewModelKtx)
    implementation(libs.liveDataKtx)
}