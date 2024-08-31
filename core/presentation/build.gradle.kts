import deps.DependenciesVersions
import deps.androidx
import deps.domainModule
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.presentation"

  composeOptions {
    kotlinCompilerExtensionVersion = DependenciesVersions.KOTLIN_COMPILER
  }

  buildFeatures {
    compose = true
  }
}

dependencies {
  androidx()
  testDeps()
  domainModule()
  testImplDeps()
  testDebugDeps()
}
