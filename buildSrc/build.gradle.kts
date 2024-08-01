plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    api(kotlin("gradle-plugin:1.9.0"))
    implementation("com.android.tools.build:gradle:8.1.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.22.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.3")
}