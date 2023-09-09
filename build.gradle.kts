plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.9.20-Beta"
}

group = "me.wawwior"
version = "1.3.1"
description = "utils"


repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.kenzie.mx/releases")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    mavenCentral()
}

dependencies {

    api("com.google.guava:guava:32.1.2-jre")
    api("org.python:jython-slim:2.7.3b1")
    api("mx.kenzie:mimic:1.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))

}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

kotlin {
    jvmToolchain(17)
}