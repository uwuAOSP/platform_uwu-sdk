/*
 * Copyright (C) 2026 UwUniverse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
