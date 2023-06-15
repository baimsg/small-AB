import com.chat.buildsrc.Dep

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {

    defaultConfig {
        namespace = "com.chat.data"
    }

    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(project(":base"))
    implementation(project(":base-android"))
    api(Dep.Hilt.library)
    //hilt
    kapt(Dep.Hilt.compiler)
}