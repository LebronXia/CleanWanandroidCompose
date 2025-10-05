plugins {
    alias(libs.plugins.wanandroid.android.feature)
    alias(libs.plugins.wanandroid.android.library.compose)
}

android {
    namespace = "com.riane.feature.home"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.compose)
    //implementation(project(":data:repository"))
    implementation(project(":domain:domian-home"))
    implementation(libs.android.constraintlayout.compose)
}