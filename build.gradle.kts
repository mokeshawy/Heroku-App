// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val kotlinVersion  = "2.2.0"
    id("com.android.application") version "8.13.2" apply false
    id("com.android.library") version "8.13.2" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
    id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false
    id("com.google.dagger.hilt.android")  version "2.57.2" apply false
    id("com.google.gms.google-services") version "4.4.3" apply false
    id("com.google.firebase.crashlytics") version "3.0.6" apply false
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
}