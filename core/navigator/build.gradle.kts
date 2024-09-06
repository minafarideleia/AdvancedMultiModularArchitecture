import deps.androidx
import deps.hilt
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.navigator"
}

dependencies {
  androidx()
  hilt()
  testDeps()
  testImplDeps()
  testDebugDeps()
}
