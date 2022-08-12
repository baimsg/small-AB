import com.chat.buildsrc.Dep

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
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
    implementation(project(":base"))
    implementation(project(":base-android"))
    implementation(project(":common-data"))
    api(Dep.Hilt.library)

    //hilt
    kapt(Dep.Hilt.compiler)
}