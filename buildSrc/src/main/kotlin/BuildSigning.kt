import com.android.build.api.dsl.ApkSigningConfig
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

sealed class BuildSigning(val name: String) {

    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>)


    object Release : BuildSigning(SigningTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File("path/release/myKeyStore.jks")
                storePassword = "release_store_password"
                keyAlias = "release_key_alias"
                keyPassword = "release_key_password"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    object ReleaseExternalQa : BuildSigning(SigningTypes.RELEASE_EXTERNAL_QA) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File("path/releaseExternalQA/myKeyStore.jks")
                storePassword = "release_store_password"
                keyAlias = "release_key_alias"
                keyPassword = "release_key_password"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    object Debug : BuildSigning(SigningTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.getByName(name) {
                storeFile = File("path/debug/myKeyStore.jks")
                storePassword = "release_store_password"
                keyAlias = "release_key_alias"
                keyPassword = "release_key_password"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

}