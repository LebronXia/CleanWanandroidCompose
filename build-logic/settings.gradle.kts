// 声明这是一个独立的 Gradle 项目
rootProject.name = "build-logic"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
    include(":convention")
}

// 允许访问主项目的版本目录
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml")) // 指向主项目的版本文件
        }
    }
}// 声明这是一个独立的 Gradle 项目

rootProject.name = "build-logic"
include(":convention")
