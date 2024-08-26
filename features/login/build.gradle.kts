import deps.androidx
import deps.dataModule
import deps.domainModule
import deps.hilt
import deps.retrofit
import deps.room
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
  id("org.jetbrains.kotlin.android")
}
apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.login"
}

dependencies {
  androidx()
  retrofit()
  dataModule()
  domainModule()
  hilt()
  room()
  testDeps()
  testImplDeps()
  testDebugDeps()
}
