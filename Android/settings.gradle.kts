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

rootProject.name = "LogiSync"
include(":app")
include(":feature:login")
include(":core:designsystem")
include(":feature:signup")
include(":core:navigation")
include(":core:data")
include(":core:firebase")
include(":core:model")
include(":core:domain")
include(":feature:onboard")
include(":core:bluetooth")
include(":service")
include(":feature:home")
include(":core:datastore")
