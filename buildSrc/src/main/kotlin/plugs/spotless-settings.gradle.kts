import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

@Suppress("INACCESSIBLE_TYPE")
configure<com.diffplug.gradle.spotless.SpotlessExtension> {

    format("xml") {
        target("**/*.xml")
        prettier(mapOf("prettier" to "2.7.1", "@prettier/plugin-xml" to "2.2.0"))
            .config(
                mapOf(
                    "parser" to "xml",
                    "tabWidth" to 4,
                    "printWidth" to 80,
                    "useTabs" to false,
                    "semi" to true,
                    "singleQuote" to false,
                    "attributeSortOrder" to arrayOf("name", "id", "type"),
                    "selfClosingTags" to arrayOf("br", "img")
                )
            )
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint("0.49.0")
            .userData(mapOf("android" to "true", "max_line_length" to "120"))
            .editorConfigOverride(mapOf("indent_size" to 2))
    }

    java {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.java"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        // eclipse()
        // The eclipse() method in the spotless plugin is used to apply the Eclipse Code Formatter to Java files.
        // While it's originally associated with Eclipse IDE, it doesn't mean that it only works with Eclipse projects.
        // The Eclipse formatter configuration can be used by other IDEs, including Android Studio.
        googleJavaFormat()
        //method is used as the formatter for Java files.
        // googleJavaFormat is a formatter that follows the Google Java Style Guide.
    }

    kotlinGradle {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.gradle.kts", "*.gradle.kts"),
                    "exclude" to listOf("**/build/**")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint("0.49.0")
            .userData(mapOf("android" to "true"))
            .editorConfigOverride(mapOf("indent_size" to 2))
    }
}

tasks.named("preBuild") {
    dependsOn("spotlessCheck")
}
tasks.named("preBuild") {
    dependsOn("spotlessApply")
}
