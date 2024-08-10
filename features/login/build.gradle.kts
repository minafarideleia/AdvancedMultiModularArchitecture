import deps.androidx
import deps.hilt
import deps.retrofit
import deps.room
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.login"
}

dependencies {
  androidx()
  retrofit()
  hilt()
  room()
  testDeps()
  testImplDeps()
  testDebugDeps()
}
