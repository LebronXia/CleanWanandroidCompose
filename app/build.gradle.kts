plugins {
    alias(libs.plugins.wanandroid.android.application)
    alias(libs.plugins.wanandroid.android.application.compose)
    alias(libs.plugins.wanandroid.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.riane.cleanwanandroidcompose"

    defaultConfig {
        applicationId = "com.riane.cleanwanandroidcompose"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        androidResources{
//            localeFil
//        }
    }

    signingConfigs {
        create("common") {
            storeFile = rootProject.file("./src/wanandroiod.jts")
            storePassword =  "123456"
            keyAlias = "android"
            keyPassword =  "123456"
        }
    }

    buildTypes {
        debug{
            signingConfig = signingConfigs["common"]
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature:feature-auth"))
    implementation(project(":feature:feature-home"))
    implementation(project(":feature:feature-hotspot"))
    implementation(project(":feature:feature_system"))
    implementation(project(":feature:feature-profile"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(project(":core:utils"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)


}