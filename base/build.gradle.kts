import com.chat.buildsrc.Dep

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Dep.compileSdk
    defaultConfig {
        namespace="com.chat.base"
        minSdk = Dep.minSdk
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
        }
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    //Kotlin
    api(Dep.Kotlin.stdlib)
    api(Dep.Kotlin.Serialization.json)
    api(Dep.Kotlin.coroutinesCore)
    //hilt
    api(Dep.Hilt.library)
    kapt(Dep.Hilt.compiler)
    //retrofit
    api(Dep.Retrofit.library)
    api(Dep.Retrofit.kotlinSerializerConverter)
    //okHttp
    api(Dep.OkHttp.library)
    api(Dep.OkHttp.loggingInterceptor)
    api(Dep.Libs.mmkv)
}
