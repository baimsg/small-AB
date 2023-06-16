import com.baimsg.buildsrc.Dep
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Dep.compileSdk

    signingConfigs {
        val properties = Properties()
        val propFile = project.file("../keystore.properties")
        if (propFile.exists()) {
            properties.load(propFile.inputStream())
        }
        create("release") {
            keyAlias = properties.getProperty("keyAlias")
            keyPassword = properties.getProperty("keyPassword")
            storeFile = file(properties.getProperty("storeFile"))
            storePassword = properties.getProperty("storePassword")
            enableV2Signing = true
            enableV1Signing = true
        }
    }

    defaultConfig {
        namespace = "com.baimsg.contact"
        applicationId = "com.baimsg.contact"
        minSdk = Dep.minSdk
        targetSdk = Dep.targetSdk
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        ndk {
            abiFilters.apply {
                add("armeabi-v7a")
                add("arm64-v8a")
            }
        }
    }

    buildTypes {
        getByName("release") {
            // 启用代码压缩、优化和混淆（由R8或者ProGuard执行）
            isMinifyEnabled = true
            // 启用资源压缩（由Android Gradle plugin执行）
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    android.applicationVariants.all {
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) outputFileName =
                "smallAB_${defaultConfig.versionName}-${defaultConfig.versionCode}-${buildType.name}.apk"
        }
    }

    flavorDimensions += "honey"
    productFlavors {
        create("pro") {
            dimension = "honey"
            resValue("string", "app_name", "smallAB")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":ui-resource"))
    implementation(project(":base"))
    implementation(project(":base-android"))
    implementation(project(":common-data"))
    //Room
    kapt(Dep.AndroidX.Room.compiler)
    //Lifecycle
    kapt(Dep.AndroidX.LifeCycle.compiler)
    api(Dep.Hilt.library)
    //hilt
    kapt(Dep.Hilt.compiler)
}