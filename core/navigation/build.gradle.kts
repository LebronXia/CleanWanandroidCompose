plugins {
    alias(libs.plugins.wanandroid.android.library)
    alias(libs.plugins.wanandroid.hilt)
}

android {
    namespace = "com.riane.navigation"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
