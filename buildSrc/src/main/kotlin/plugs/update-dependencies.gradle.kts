import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply<VersionsPlugin>()

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "${project.rootDir}/reports/dependencyUpdates"
    reportfileName = "report"
}