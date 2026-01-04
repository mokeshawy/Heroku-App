plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3").version("3.7.3")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")

}

apollo {
    service("service") {
        packageName.set("com.heroku_app")
        schemaFile.set(file(path = "src/main/graphql/schema.graphqls"))
    }
}

android {

    signingConfigs {
        create("release") {
            keyAlias = "keyAlis"
            keyPassword = "Mohamed**Keshawy"
            storeFile = file("heroku-key-store.jks")
            storePassword = "Mohamed**Keshawy"
        }
    }

    namespace = "com.heroku_app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.heroku_app"
        minSdk = 24
        targetSdk = 36
        versionCode = 3
        versionName = "1.2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        externalNativeBuild {
            // For ndk-build, instead use the ndkBuild block.
            cmake {
                // Passes optional arguments to CMake.
                arguments("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
            }
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            externalNativeBuild {
                cmake {
                    arguments(arguments = arrayOf("-DDEBUG_BUILD=1"))
                }
            }
        }

        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            externalNativeBuild {
                cmake {
                    arguments(arguments = arrayOf("-DDEBUG_BUILD=1"))
                }
            }
        }
    }

    flavorDimensions.add("version")
    productFlavors {
        create("dev") {
            dimension = "version"
            versionNameSuffix = ".dev"
            applicationIdSuffix = ".dev"
            externalNativeBuild {
                cmake {
                    arguments(arguments = arrayOf("-DAPP_FLAVOR=dev"))
                }
            }
        }

        create("live") {
            dimension = "version"
            externalNativeBuild {
                cmake {
                    arguments(arguments = arrayOf("-DAPP_FLAVOR=live"))
                }
            }
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

    externalNativeBuild {
        cmake {
            path = file(path = "src/cpp/CMakeLists.txt")
            version = "4.1.2"
        }
    }
}

dependencies {

    implementation(project(path = ":core"))
    implementation("androidx.core:core-ktx:1.17.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    //noinspection GradleDependency
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    ksp("androidx.hilt:hilt-compiler:1.3.0")

}