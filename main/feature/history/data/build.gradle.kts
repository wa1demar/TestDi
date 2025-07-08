plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ua.waldemar.customdi.main.feature.history.data"
    compileSdk = 36

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(projects.api)
    implementation(projects.main.feature.history.domain)
    implementation(projects.main.shared.domain)
    implementation(projects.main.shared.data)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
//    implementation(projects.core.di)
}