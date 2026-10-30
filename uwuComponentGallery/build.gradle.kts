/*
 * SPDX-FileCopyrightText: UwUniverse
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
    id("com.android.application") version "8.6.0"
    id("com.android.library") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.20"
}

android {
    namespace = "org.uwuaosp.compose.gallery"

    defaultConfig {
        applicationId = "org.uwuaosp.compose.gallery"
        versionCode = 1
        versionName = "1.0"
        minSdk = 26
    }

    sourceSets.getByName("main") {
        kotlin.setSrcDirs(listOf("src"))
        res.setSrcDirs(listOf("res"))
        manifest.srcFile("AndroidManifest.xml")
    }

    compileSdk = 35
    buildFeatures { compose = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes { release { isMinifyEnabled = false } }
}

kotlin { compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17) } }

dependencies {
    implementation(project(":uwuCompose"))
    implementation("androidx.activity:activity-compose:1.9.0")
}
