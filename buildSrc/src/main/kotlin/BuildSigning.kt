import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationProductFlavor
import org.gradle.api.NamedDomainObjectContainer

sealed class BuildSigning(val name: String) {

    abstract fun create(namedDomainObjectContainer:  NamedDomainObjectContainer<out ApkSigningConfig>)

}