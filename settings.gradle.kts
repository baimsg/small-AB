pluginManagement {
    repositories {
        maven { setUrl("https://plugins.gradle.org/m2/") }
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
        google()
        mavenLocal()
    }
}
rootProject.name = "VideoHoney"
include(":app")
include(":base")
include(":base-android")
include(":common-data")
