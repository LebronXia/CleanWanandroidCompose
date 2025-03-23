build-logic/
├── conventions/
│   ├── android-application/  # 应用模块配置
│   ├── android-library/      # 库模块配置
│   ├── jvm/                 # 纯Kotlin模块配置
│   └── primitives/           # 基础配置块
├── version-catalogs/        # 版本目录扩展
└── build.gradle.kts         # 构建配置

✅ 所有模块的构建配置集中管理
✅ 避免重复的 Gradle 配置代码
✅ 快速同步项目级变更（如 SDK 版本升级）
✅ 提升大型项目的构建性能

创建普通文件夹 build-logic
配置其内部的 Gradle 文件
在主项目中通过 includeBuild 引入
编写 Convention 插件代码
通过预编译插件机制自动发布插件
这种设计可以大幅减少重复的 Gradle 配置代码，是大型 Android 项目的标准实践