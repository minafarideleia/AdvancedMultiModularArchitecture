import deps.androidx
import deps.hilt
import deps.loginModule
import deps.okHttp
import deps.retrofit
import deps.room
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
  id(plugs.BuildPlugins.KOTLIN_ANDROID)
  kotlin(plugs.BuildPlugins.KAPT)
}

android {
    namespace = "com.minafarid.login"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    androidx()
    hilt()
    room()
    testDeps()
    testImplDeps()
    testDebugDeps()
}