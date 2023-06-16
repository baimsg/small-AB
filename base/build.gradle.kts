import com.baimsg.buildsrc.Dep

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.baimsg.base"

    defaultConfig {
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
    //Rom
    api(Dep.AndroidX.Room.common)
    api(Dep.AndroidX.Room.runtime)
    api(Dep.AndroidX.Room.ktx)
    api(Dep.AndroidX.Room.paging)
    kapt(Dep.AndroidX.Room.compiler)
}
