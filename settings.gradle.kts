pluginManagement {
    repositories {
        maven { setUrl("https://plugins.gradle.org/m2/") }
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
        google()
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { setUrl("https://plugins.gradle.org/m2/") }
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
        google()
        mavenLocal()
    }
}
rootProject.name = "smallAB"
include(":app")
include(":base")
include(":base-android")
include(":common-data")
include(":ui-resource")
