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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "CustomDI"
include(":app")
include(":main:model")
include(":main:view")
include(":main:shared:domain")
include(":main:shared:data")
include(":main:publicapi")
include(":main:publicapi:domain")
include(":main:publicapi:data")
include(":api")
include(":core:theme")
include(":core:di")
include(":appfeature:common")
include(":appfeature:forgot:presentation")
include(":appfeature:forgot:domain")
include(":appfeature:forgot:data")
