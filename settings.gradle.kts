pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Sim Insights"
include(":app")
include(":ads")
include(":analytics")
include(":splash:presentation")
include(":core:drawable")
include(":core:font")
include(":core:values")
include(":core:common")
include(":core:framework")
include(":home")
include(":result:domain")
include(":result:data")
include(":result:presentation")
include(":server:data")
include(":server:domain")
include(":server:presentation")
include(":web")
include(":setting")
include(":premium")
include(":recent:domain")
include(":recent:data")
include(":recent:presentation")
