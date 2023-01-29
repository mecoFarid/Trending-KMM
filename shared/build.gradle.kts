import src.main.java.toInt

@Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization").version(libs.versions.kotlin.get())
    id("com.android.library")
    alias(libs.plugins.sqlDelight)
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
            isStatic = false
        }
        pod("Kingfisher") {
            version = "~> 6.3.1-xcode13"
        }
        pod("lottie-ios")
    }
    
    sourceSets {
        val commonMain by getting{
            dependencies{
                implementation(libs.kotlinxSerialization)
                implementation(libs.kotlinxCoroutinesCore)
                implementation(libs.ktorCore)
                implementation(libs.ktorClientNegotiation)
                implementation(libs.ktorKotlinxJsonSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelightAndroidDriver)
                implementation(libs.ktorAndroidClient)
            }
        }
        val androidTest by getting {
            dependencies{
                implementation(libs.androidCoreTestKtx)
                implementation(libs.sqldelightJavaDriver)
                //Fixme: Ideally, we have to use SQLite version that is compatible with Android
                // minSDK but required version is not compiled with Apple M1 chip, and tests are not
                // running on Development Machine with M1 chip
//                implementation(libs.sqliteJava.get().toString()) {
//                    // Override the version of sqlite used by sqlite-driver to match minSDK
//                    // (21 for current project)
//                    version {
//                        strictly("3.8.10.2"libs.versions.sqliteJava.get())
//                    }
//                }
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(libs.sqldelightIosDriver)
                implementation(libs.ktorIosClient)
            }
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

sqldelight {
    databases {
        create("TrendingDatabase") {
            packageName.set("com.mecofarid.trending.libs.db.sqldelight")
        }
    }
}