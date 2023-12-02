plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "es.chebetos.advent2023"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(21)
}

tasks {
    test {
        useJUnitPlatform()
    }
    wrapper {
        gradleVersion = "8.5"
    }
}
