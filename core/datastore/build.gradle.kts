import deps.dataStore
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.datastore"
}

dependencies {
  dataStore()
  testDeps()
  testImplDeps()
  testDebugDeps()
}
