package src.main.java

import org.gradle.api.provider.Provider

fun Provider<String>.toInt() = get().toInt()