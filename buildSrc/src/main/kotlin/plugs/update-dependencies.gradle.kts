import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.util.Locale

apply<VersionsPlugin>()

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
// exclude non stable versions
    resolutionStrategy {
        componentSelection {
            all {
                if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                    reject("Release candidate")
                }
            }
        }
    }

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "${project.rootDir}/reports/dependencyUpdates"
    reportfileName = "report"
}


fun isNonStable(version: String): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA").any { version.uppercase(Locale.ROOT).contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}