import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer


sealed class BuildFlavor(val name: String) {

    // its used for app
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ) : ApplicationProductFlavor

    // its used for modules
    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>
    ) : LibraryProductFlavor


}