package com.chat.buildsrc

import org.gradle.api.JavaVersion

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
object Dep {
    val javaVersion = JavaVersion.VERSION_11
    const val kotlinJvmTarget = "11"
    const val kotlinVer = "1.7.10"

    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32

    const val group = "com.baimsg.chat"

    object MaterialDesign {
        const val material = "com.google.android.material:material:1.4.0"
    }

    object Retrofit {
        private const val retrofit = "2.9.0"
        const val library = "com.squareup.retrofit2:retrofit:$retrofit"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofit"
        const val kotlinSerializerConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }


    object OkHttp {
        private const val okhttp = "4.9.3"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp"
        const val library = "com.squareup.okhttp3:okhttp:$okhttp"
    }

    object AndroidX {
        const val core = "androidx.core:core:1.7.0"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val annotation = "androidx.annotation:annotation:1.3.0"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
        const val multiDex = "androidx.multidex:multidex:2.0.1"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        object ConstraintLayout {
            private const val version = "2.1.3"
            const val library = "androidx.constraintlayout:constraintlayout:$version"
        }

        object Activity {
            private const val version = "1.4.0"
            const val library = "androidx.activity:activity-ktx:$version"
        }

        object Fragment {
            private const val version = "1.4.1"
            const val library = "androidx.fragment:fragment-ktx:$version"
        }

        object Hilt {
            private const val androidxHilt = "1.0.0"
            const val work = "androidx.hilt:hilt-work:$androidxHilt"
            const val navigation = "androidx.hilt:hilt-navigation-compose:$androidxHilt"
        }

        object Navigation {
            private const val version = "2.4.2"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object LifeCycle {
            private const val ver = "2.5.0-rc01"
            const val vmKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$ver"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$ver"
            const val liveDate = "androidx.lifecycle:lifecycle-livedata-ktx:$ver"
            const val vmSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$ver"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$ver"
        }
    }

    object Kotlin {
        private const val coroutines = "1.6.1"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$kotlinVer"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVer"

        object Serialization {
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
            const val protobuf = "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3"
        }
    }

    object Bugly {
        private const val version = "latest.release"
        const val upgrade = "com.tencent.bugly:crashreport_upgrade:$version"
        const val native = "com.tencent.bugly:nativecrashreport:$version"
    }

    object Logger {
        const val library = "com.orhanobut:logger:2.2.0"
    }

    object Hilt {
        private const val hilt = "2.42"
        const val library = "com.google.dagger:hilt-android:$hilt"
        const val compiler = "com.google.dagger:hilt-compiler:$hilt"
    }

    object Libs {
        const val mmkv = "com.tencent:mmkv-static:1.2.10"
        const val glide = "com.github.bumptech.glide:glide:4.11.0"
        const val permissionX = "com.guolindev.permissionx:permissionx:1.6.4"
        const val pudding = "com.github.o0o0oo00:Pudding:1.2.1"

        object Dialog {
            private const val ver = "3.3.0"
            const val core = "com.afollestad.material-dialogs:core:$ver"
            const val input = "com.afollestad.material-dialogs:input:$ver"
            const val files = "com.afollestad.material-dialogs:files:$ver"
            const val color = "com.afollestad.material-dialogs:color:$ver"
            const val datetime = "com.afollestad.material-dialogs:datetime:$ver"
            const val bottomSheets = "com.afollestad.material-dialogs:bottomsheets:$ver"
            const val lifecycle = "com.afollestad.material-dialogs:lifecycle:$ver"
        }
    }


}