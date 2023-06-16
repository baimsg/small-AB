import com.baimsg.buildsrc.Dep

plugins {
    kotlin("plugin.serialization") version "1.8.10" apply false
    kotlin("kapt") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.10" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    plugins.withType<JavaBasePlugin>().configureEach {
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(11))
            }
        }
    }
    pluginManager.withPlugin("org.jetbrains.kotlin.kapt") {
        extensions.getByType<org.jetbrains.kotlin.gradle.plugin.KaptExtension>().correctErrorTypes = true
    }

    // Configure Android projects
    pluginManager.withPlugin("com.android.application") {
        configureAndroidProject()
    }
    pluginManager.withPlugin("com.android.library") {
        configureAndroidProject()
    }
    pluginManager.withPlugin("com.android.test") {
        configureAndroidProject()
    }
}

fun Project.configureAndroidProject() {
    extensions.configure<com.android.build.gradle.BaseExtension> {
        compileSdkVersion(Dep.compileSdk)

        defaultConfig {
            minSdk = Dep.minSdk
            targetSdk = Dep.targetSdk
        }

        // Can remove this once https://issuetracker.google.com/issues/260059413 is fixed.
        // See https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11

            // https://developer.android.com/studio/write/java8-support
            isCoreLibraryDesugaringEnabled = true
        }

    }

    dependencies {
        "coreLibraryDesugaring"(Dep.Libs.desugar)
    }
}