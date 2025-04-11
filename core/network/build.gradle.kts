plugins {
    alias(libs.plugins.wanandroid.android.library)
    alias(libs.plugins.wanandroid.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.riane.network"
}

dependencies {

    api(libs.coil.kt)
    api(libs.coil.kt.svg)
    api(libs.kotlinx.serialization.json)
    api(libs.okhttp.logging)
    api(libs.retrofit.core)
    api(libs.retrofit.kotlin.serialization)
    testImplementation(libs.kotlinx.coroutines.test)
}