plugins {
    alias(libs.plugins.wanandroid.android.library)
    alias(libs.plugins.wanandroid.hilt)
}

android {
    namespace = "com.riane.repository"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.paging.compose)
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":data:remote"))
    implementation(project(":domain:domain-auth"))
    implementation(project(":domain:domian-home"))
}