plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
}

dependencies {
    api("androidx.core:core-ktx:1.17.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    api("androidx.work:work-runtime-ktx:2.11.0")
    api("androidx.activity:activity-compose:1.12.2")
    //noinspection GradleDependency
    api(platform("androidx.compose:compose-bom:2024.09.00"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.ui:ui-tooling")
    api("androidx.compose.material3:material3")
    implementation("androidx.core:core-ktx:1.17.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")


    // firebase bom
    api(platform("com.google.firebase:firebase-bom:33.16.0"))
    api("com.google.firebase:firebase-crashlytics")
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-analytics")
    api("com.google.firebase:firebase-config-ktx")


    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    ksp("androidx.hilt:hilt-compiler:1.3.0")


    // Paging
    val paging = "3.3.6"
    api("androidx.paging:paging-runtime-ktx:$paging")
    api("androidx.paging:paging-compose:$paging")
    api("com.google.accompanist:accompanist-pager:0.36.0")
    api("com.google.accompanist:accompanist-pager-indicators:0.36.0")


    // Gson
    api("com.google.code.gson:gson:2.13.2")


    // Retrofit
    val retrofit = "3.0.0"
    api("com.squareup.retrofit2:retrofit:$retrofit")
    api("com.squareup.retrofit2:converter-gson:$retrofit")


    // Crypto
    api("androidx.security:security-crypto:1.1.0-alpha06")


    // Coil
    api("io.coil-kt:coil-compose:2.7.0")


    // Timber
    api("com.jakewharton.timber:timber:5.0.1")


    //Android Remote Debugger
    debugApi("com.github.zerobranch.android-remote-debugger:debugger:1.1.2")
    releaseApi("com.github.zerobranch.android-remote-debugger:noop:1.1.2")


    //pluto
    val plutoVersion = "3.0.1"
    debugApi("com.androidpluto:pluto:$plutoVersion")
    releaseApi("com.androidpluto:pluto-no-op:$plutoVersion")
    debugApi("com.androidpluto.plugins:bundle-core:$plutoVersion")
    releaseApi("com.androidpluto.plugins:bundle-core-no-op:$plutoVersion")
}