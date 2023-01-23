import src.main.java.toInt

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization").version(libs.versions.kotlin.get())
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = libs.versions.version.get()
        ios.deploymentTarget = libs.versions.deploymentTarget.get()
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
        pod("Kingfisher") {
            version = "~> 6.3.1-xcode13"
        }
    }
    
    sourceSets {
        val commonMain by getting{
            dependencies{
                implementation(libs.kotlinxJsonSerialization)
                implementation(libs.kotlinxCoroutinesCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.mecofarid.trending"
    compileSdk = libs.versions.compileTargetSdk.toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.toInt()
        targetSdk = libs.versions.compileTargetSdk.toInt()
    }
}