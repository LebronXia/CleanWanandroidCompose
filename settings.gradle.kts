pluginManagement {
    includeBuild("build-logic") // 关键配置
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://mirrors.tencent.com/nexus/repository/maven-public") }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://mirrors.tencent.com/nexus/repository/maven-public") }
        mavenCentral()
    }
}

rootProject.name = "CleanWanandroidCompose"
include(":app")
include(":core:network")
include(":core:database")
include(":core:ui")
include(":core:utils")
include(":core:preferences")
include(":core:navigation")
include(":domain:base")
include(":domain:domain-auth")
include(":data:repository")
include(":data:remote")
include(":data:local")
include(":data:cache")
include(":feature:feature-auth")
include(":core:common")
