import build.BuildConfig
import build.BuildCreator
import build.BuildDimensions
import deps.DependenciesVersions
import deps.androidx
import deps.dataModule
import deps.dataStore
import deps.dataStoreModule
import deps.domainModule
import deps.hilt
import deps.homeModule
import deps.kotlinx
import deps.loginModule
import deps.navigatorModule
import deps.okHttp
import deps.presentationModule
import deps.protoDataStoreModule
import deps.retrofit
import deps.room
import deps.signupModule
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import falvors.BuildFlavor
import release.ReleaseConfig
import signing.BuildSigning
import signing.SigningTypes
import test.TestBuildConfig

plugins {
  id(plugs.BuildPlugins.ANDROID_APPLICATION)
  id(plugs.BuildPlugins.KOTLIN_ANDROID)
  id(plugs.BuildPlugins.ANDROID)
  id(plugs.BuildPlugins.KAPT)
  id(plugs.BuildPlugins.KTLINT)
  id(plugs.BuildPlugins.SPOTLESS)
  id(plugs.BuildPlugins.DETEKT)
  id(plugs.BuildPlugins.UPDATE_DEPS_VERSIONS)
  id(plugs.BuildPlugins.DOKKA)
  id(plugs.BuildPlugins.HILT) version deps.DependenciesVersions.HILT
}

android {
  namespace = BuildConfig.APP_ID
  compileSdk = BuildConfig.COMPILE_SDK_VERSION

  defaultConfig {
    applicationId = BuildConfig.APP_ID
    minSdk = BuildConfig.MIN_SDK_VERSION
    targetSdk = BuildConfig.TARGET_SDK_VERSION
    versionCode = ReleaseConfig.VERSION_CODE
    versionName = ReleaseConfig.VERSION_NAME

    testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  signingConfigs {
    BuildSigning.Release(project).create(this)
    BuildSigning.ReleaseExternalQa(project).create(this)
    BuildSigning.Debug(project).create(this)
  }

  buildTypes {
    BuildCreator.Release(project).create(this).apply {
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
      signingConfig = signingConfigs.getByName(SigningTypes.RELEASE)
    }
    BuildCreator.Debug(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.DEBUG)
    }
    BuildCreator.ReleaseExternalQa(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.RELEASE_EXTERNAL_QA)
    }
  }
  flavorDimensions.add(BuildDimensions.APP)
  flavorDimensions.add(BuildDimensions.STORE)

  productFlavors {
    BuildFlavor.Google.create(this)
    BuildFlavor.Huawei.create(this)
    BuildFlavor.Client.create(this)
    BuildFlavor.Driver.create(this)
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = DependenciesVersions.KOTLIN_COMPILER
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  loginModule()
  homeModule()
  signupModule()
  dataModule()
  dataStoreModule()
  protoDataStoreModule()
  dataStore()
  kotlinx()
  navigatorModule()
  domainModule()
  presentationModule()
  androidx()
  hilt()
  room()
  okHttp()
  retrofit()
  testDeps()
  testImplDeps()
  testDebugDeps()
}
