import org.gradle.api.Project
import java.util.Properties

private const val LOCAL_PROPERTIES_FILE_NAME = "dev_credentials.properties"

fun Project.getLocalProperty(propertyName:String) : String{
    val localProperties = Properties().apply {
        val localPropertiesFile =
            project.rootProject.file(LOCAL_PROPERTIES_FILE_NAME)
        if(localPropertiesFile.exists()){
            load(localPropertiesFile.inputStream())
        }
    }
    return localProperties.getProperty(propertyName) ?: throw
    NoSuchFieldException("Property not found: $propertyName")
}