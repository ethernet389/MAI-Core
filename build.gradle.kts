plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
    application
}

group = "com.github.ethernet389"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    //Jama (Java Matrix)
    val jama_version = "1.0.3"
    implementation("gov.nist.math:jama:$jama_version")

    //JSON Serialization
    val json_serialization_version = "1.5.1"
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$json_serialization_version")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("math.MainKt")
}