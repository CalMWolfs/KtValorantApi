plugins {
    java
    kotlin("jvm") version "1.9.23"
}

group = "com.calmwolfs.ktvalorantapi"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io") {
        content {
            includeGroupByRegex("com\\.github\\..*")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.github.CalMWolfs:ValorantModelApi:1.2.1")
}

kotlin {
    jvmToolchain(17)
}