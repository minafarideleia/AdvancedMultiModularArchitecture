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

rootProject.name = "AdvancedMultiModularArchitecture"
include(":app")
include(":features:login")
include(":features:home")
include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":core:datastore")
include(":core:protodatastore")
include(":core:navigator")
include(":features:signup")
