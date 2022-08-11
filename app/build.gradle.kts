import com.chat.buildsrc.Dep

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Dep.compileSdk

    defaultConfig {
        applicationId = "com.chat.honey"
        minSdk = Dep.minSdk
        targetSdk = Dep.targetSdk
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {

        }
    }
    compileOptions {
        sourceCompatibility = Dep.javaVersion
        targetCompatibility = Dep.javaVersion
    }
    kotlinOptions {
        jvmTarget = Dep.kotlinJvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

}