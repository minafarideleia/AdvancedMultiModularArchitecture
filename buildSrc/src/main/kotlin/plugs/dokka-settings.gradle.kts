import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

apply<DokkaPlugin>()

tasks {
    register<DokkaTask>("cDokkaHtml") {
        outputDirectory.set(file("${project.rootDir}/docs"))

        // Suppress obvious functions like default toString or equals. Defaults to true
        suppressObviousFunctions.set(false)

        // Suppress all inherited members that were not overriden in a given class.
        // Eg. using it you can suppress toString or equals functions but you can't suppress componentN or copy on data class. To do that use with suppressObviousFunctions
        // Defaults to false
        suppressInheritedMembers.set(true)

        // Used to prevent resolving package-lists online. When this option is set to true, only local files are resolved
        offlineMode.set(false)

        dokkaSourceSets {
            moduleName.set("ModularizationApp")
            configureEach { // Or source set name, for single-platform the default source sets are `main` and `test`

                // Used to remove a source set from documentation, test source sets are suppressed by default
                suppress.set(false)

                // Use to include or exclude non public members
                includeNonPublic.set(false)

                // Do not output deprecated members. Applies globally, can be overridden by packageOptions
                skipDeprecated.set(false)

                // Emit warnings about not documented members. Applies globally, also can be overridden by packageOptions
                reportUndocumented.set(true)

                // Do not create index pages for empty packages
                skipEmptyPackages.set(true)

                // This name will be shown in the final output
                displayName.set("JVM")

                // Platform used for code analysis. See the "Platforms" section of this readme
                platform.set(org.jetbrains.dokka.Platform.jvm)

                // List of files or directories containing sample code (referenced with @sample tags)
                samples.from("samples/basic.kt", "samples/advanced.kt")

                // By default, sourceRoots are taken from Kotlin Plugin and kotlinTasks, following roots will be appended to them
                // Repeat for multiple sourceRoots
                sourceRoots.from(file("src"))

                // Specifies the location of the project source code on the Web.
                // If provided, Dokka generates "source" links for each declaration.
                // Repeat for multiple mappings
                sourceLink {
                    // Unix based directory relative path to the root of the project (where you execute gradle respectively).
                    localDirectory.set(file("src/main/kotlin"))

                    // URL showing where the source code can be accessed through the web browser
                    remoteUrl.set(
                        java.net.URL(
                            "https://github.com/minafarideleia/AdvancedMultiModularArchitecture"
                        )
                    )
                    // Suffix which is used to append the line number to the URL. Use #L for GitHub
                    remoteLineSuffix.set("#L")
                }

                // Used for linking to JDK documentation
                jdkVersion.set(8)

                // Disable linking to online kotlin-stdlib documentation
                noStdlibLink.set(false)

                // Disable linking to online JDK documentation
                noJdkLink.set(false)

                // Disable linking to online Android documentation (only applicable for Android projects)
                noAndroidSdkLink.set(false)

                // Allows linking to documentation of the project"s dependencies (generated with Javadoc or Dokka)
                // Repeat for multiple links
                externalDocumentationLink {
                    // Root URL of the generated documentation to link with. The trailing slash is required!
                    url.set(URL("https://github.com/minafarideleia/AdvancedMultiModularArchitecture/docs"))

                    // If package-list file is located in non-standard location
                    // packageListUrl = URL("file:///home/user/localdocs/package-list")
                }

                // Allows to customize documentation generation options on a per-package basis
                // Repeat for multiple packageOptions
                // If multiple packages match the same matchingRegex, the longuest matchingRegex will be used
                perPackageOption {
                    matchingRegex.set("kotlin($|\\.).*") // will match kotlin and all sub-packages of it
                    // All options are optional, default values are below:
                    skipDeprecated.set(false)
                    reportUndocumented.set(true) // Emit warnings about not documented members
                    includeNonPublic.set(false)
                }
                // Suppress a package
                perPackageOption {
                    matchingRegex.set(""".*\.internal.*""") // will match all .internal packages and sub-packages
                    suppress.set(true)
                }

                // Include generated files in documentation
                // By default Dokka will omit all files in folder named generated that is a child of buildDir
                suppressGeneratedFiles.set(false)
            }

        }
    }
}
