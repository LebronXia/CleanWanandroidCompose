plugins {
    alias(libs.plugins.wanandroid.android.feature)
    alias(libs.plugins.wanandroid.android.library.compose)
}

android {
    namespace = "com.riane.feature.auth"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(project(":data:repository"))
    implementation(project(":domain:domain-auth"))
}