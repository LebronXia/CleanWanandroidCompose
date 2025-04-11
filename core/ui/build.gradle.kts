plugins {
    alias(libs.plugins.wanandroid.android.library)
    alias(libs.plugins.wanandroid.hilt)
}

android {
    namespace = "com.riane.ui"
}

dependencies {

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    //api(libs.androidx.compose.material3.navigationSuite)
    api(libs.androidx.compose.ui.util)

    implementation(libs.coil.kt.compose)
}