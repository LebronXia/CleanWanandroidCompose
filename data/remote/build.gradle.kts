plugins {
    alias(libs.plugins.wanandroid.android.library)
    alias(libs.plugins.wanandroid.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.riane.remote"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(project(":core:network"))
    implementation(project(":domain:domain-auth"))
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    ksp("org.mapstruct:mapstruct-processor:1.5.5.Final")
}