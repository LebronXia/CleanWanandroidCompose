plugins {
    alias(libs.plugins.wanandroid.android.feature)
    alias(libs.plugins.wanandroid.android.library.compose)
}

android {
    namespace = "com.riane.feature.hotspot"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(project(":data:repository"))
    implementation(project(":domain:domain-auth"))
}