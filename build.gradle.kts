import io.gitlab.arturbosch.detekt.Detekt

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("android").version(libs.versions.kotlin.get()).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin.get()).apply(false)
    id("com.android.application").version(libs.versions.androidGradlePlugin.get()).apply(false)
    id("com.android.library").version(libs.versions.androidGradlePlugin.get()).apply(false)
    id("org.jetbrains.kotlin.jvm").version(libs.versions.kotlin.get()).apply(false)
    id(libs.plugins.detekt.get().toString()).version(libs.versions.detekt.get())
}

subprojects {
    // Jetpack Compose violates lots of code styles in Kotlin,
    // so ignoring Detekt on module with Jetpack Compose
    if (project.name != "androidApp")
        applyDetekt(project.name)
}

fun applyDetekt(projectName: String) {
    project(projectName) {
        // Version should be inherited from parent
        apply(plugin = rootProject.libs.plugins.detekt.get().toString())

        detekt {
            // Otherwise files in KMM's shared module won't be scanned.
            // https://github.com/detekt/detekt/issues/3664
            source = files("${rootProject.rootDir}/$projectName/src")

            // point to your custom config defining rules to run, overwriting default behavior
            config = files("$rootDir/detekt_config.yml")
        }

        tasks.withType(Detekt::class).configureEach {
            reports {
                html.required.set(false)
                // observe findings in your browser with structure and code snippets
                html.required.set(false) // checkstyle like format mainly for integrations like Jenkins
                html.required.set(false)
                // similar to the console output, contains issue signature to manually edit baseline files
                html.required.set(false)
                // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
