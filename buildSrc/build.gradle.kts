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
}