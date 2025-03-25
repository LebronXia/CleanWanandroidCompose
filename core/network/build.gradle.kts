plugins {
    alias(libs.plugins.wanandroid.android.library)
    alias(libs.plugins.wanandroid.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.riane.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    testImplementation(libs.kotlinx.coroutines.test)
}