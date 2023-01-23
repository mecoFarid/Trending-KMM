@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("android").version(libs.versions.kotlin.get()).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin.get()).apply(false)
    id("com.android.application").version(libs.versions.androidGradlePlugin.get()).apply(false)
    id("com.android.library").version(libs.versions.androidGradlePlugin.get()).apply(false)
    id("org.jetbrains.kotlin.jvm").version(libs.versions.kotlin.get()).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
