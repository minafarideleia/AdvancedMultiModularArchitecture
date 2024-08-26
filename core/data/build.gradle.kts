import deps.chucker
import deps.dataStore
import deps.domainModule
import deps.hilt
import deps.okHttp
import deps.protoDataStoreModule
import deps.retrofit
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.data"
}

dependencies {
  domainModule()
  okHttp()
  protoDataStoreModule()
  chucker()
  retrofit()
  dataStore()
  hilt()
  testDeps()
  testImplDeps()
  testDebugDeps()
}
