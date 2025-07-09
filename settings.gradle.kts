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

include(":appfeature:common")
include(":appfeature:forgot:presentation", ":appfeature:forgot:domain", ":appfeature:forgot:data")

include(":main:view", ":main:model")

include(":main:feature:history:data", ":main:feature:history:domain", ":main:feature:history:presentation")
include(":main:feature:settings:data", ":main:feature:settings:domain", ":main:feature:settings:presentation")
include(":main:feature:updatepassword:data", ":main:feature:updatepassword:domain", ":main:feature:updatepassword:presentation")
include(":main:feature:withdraw:data", ":main:feature:withdraw:domain", ":main:feature:withdraw:presentation")

include(":main:shared:domain", ":main:shared:data")

include(":main:publicapi", ":main:publicapi:domain", ":main:publicapi:data")

include(":api")
include(":core:theme")
include(":core:di")
include(":core:feature")
