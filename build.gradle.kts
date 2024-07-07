plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.calmwolfs.ktvalorantapi"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}