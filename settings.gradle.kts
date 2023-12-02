plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
rootProject.name = "advent-of-code-2023"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
