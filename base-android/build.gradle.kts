import com.baimsg.buildsrc.Dep

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.baimsg.base.android"

    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}
dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(project(":ui-resource"))
    api(project(":base"))
    api(Dep.AndroidX.coreKtx)
    api(Dep.AndroidX.appcompat)
    api(Dep.AndroidX.annotation)
    api(Dep.AndroidX.recyclerview)
    api(Dep.AndroidX.swipeRefreshLayout)
    api(Dep.MaterialDesign.material)

    //协程
    api(Dep.Kotlin.coroutinesAndroid)

    //ViewModel
    api(Dep.AndroidX.LifeCycle.vmKtx)
    api(Dep.AndroidX.LifeCycle.liveDate)
    api(Dep.AndroidX.LifeCycle.vmSavedState)
    api(Dep.AndroidX.LifeCycle.runtimeKtx)

    //navigation
    api(Dep.AndroidX.Navigation.fragmentKtx)
    api(Dep.AndroidX.Navigation.uiKtx)

    implementation(Dep.Hilt.library)
    //hilt
    kapt(Dep.Hilt.compiler)

    api(Dep.AndroidX.multiDex)
    api(Dep.Libs.glide)
    api(Dep.Libs.pudding)
    api(Dep.Libs.permissionX)
    api(Dep.Libs.baseAdapter)
    api(Dep.Libs.Dialog.core)
    api(Dep.Libs.Dialog.input)
    api(Dep.Libs.Dialog.datetime)
    api(Dep.Libs.Dialog.color)
    api(Dep.Libs.Dialog.files)
    api(Dep.Libs.Dialog.bottomSheets)
    api(Dep.Libs.Dialog.lifecycle)

    //Bugly
    api(Dep.Bugly.upgrade)
    api(Dep.Bugly.native)
}