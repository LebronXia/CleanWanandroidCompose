具体实现场景
1. Domain 层（独立模块）
kotlin
// domain 模块的 build.gradle
plugins {
id("kotlin") // 纯 Kotlin 模块，无 Android 依赖
}

dependencies {
// 不依赖任何其他模块
}
2. Data 层（依赖 Domain 层）
kotlin
// data 模块的 build.gradle
plugins {
id("com.android.library")
id("kotlin-kapt")
}

dependencies {
implementation(project(":domain")) // 依赖 Domain 层
implementation("com.squareup.retrofit2:retrofit:2.9.0") // 第三方库
}
3. Feature 层（依赖 Domain 层）
kotlin
// feature 模块的 build.gradle
plugins {
id("com.android.library")
}

dependencies {
implementation(project(":domain")) // 依赖 Domain 层
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // Android 框架
}
- 为什么 Domain 层在最底层？
- 业务核心：所有业务规则、实体模型、接口契约在此定义，是系统的“唯一真相源”。
- 技术无关性：Domain 层不关心数据来自网络还是数据库，也不依赖 UI 框架，可独立测试。
- 长期稳定：即使替换数据源（如 Retrofit → Ktor）或重构 UI（XML → Compose），Domain 层无需修改。
- 依赖倒置原则（DIP）​：高层模块（Data/Feature）依赖抽象（Domain 层的接口），而非具体实现