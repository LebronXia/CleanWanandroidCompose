plugins {
    alias(libs.plugins.wanandroid.android.library)
    alias(libs.plugins.wanandroid.android.library.compose)
    alias(libs.plugins.wanandroid.hilt)
}

android {
    namespace = "com.riane.utils"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    // kotlin序列化
    implementation(libs.kotlinx.serialization.json)
    // 吐司框架：https://github.com/getActivity/Toaster
    implementation(libs.toaster)
    // 权限框架：https://github.com/getActivity/XXPermissions
    implementation(libs.xxpermissions)
    //日志框架
    // https://github.com/JakeWharton/timber
    implementation(libs.timber)
}