/*
 * SPDX-FileCopyrightText: The uwuAOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "org.uwuaosp.compose"
    compileSdk = 35
    defaultConfig { minSdk = 26 }
    sourceSets.getByName("main") {
        kotlin.setSrcDirs(listOf("src"))
        res.setSrcDirs(listOf("res"))
        manifest.srcFile("AndroidManifest.xml")
    }
    buildFeatures { compose = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin { compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17) } }

dependencies {
    // Match the AndroidX sources supplied by the platform tree.
    api("androidx.compose.animation:animation:1.12.0-alpha01")
    api("androidx.compose.foundation:foundation:1.12.0-alpha01")
    api("androidx.compose.foundation:foundation-layout:1.12.0-alpha01")
    api("androidx.compose.material3:material3:1.5.0-alpha16")
    api("androidx.compose.material:material-icons-extended:1.7.0-alpha01")
    api("androidx.compose.runtime:runtime:1.12.0-alpha01")
    api("androidx.compose.runtime:runtime-saveable:1.12.0-alpha01")
    api("androidx.compose.ui:ui:1.12.0-alpha01")
    api("androidx.compose.ui:ui-graphics:1.12.0-alpha01")
}
