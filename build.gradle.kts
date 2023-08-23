plugins {
    `java-library`
    `maven-publish`
}

group = "me.wawwior"
version = "1.3.1"
description = "utils"
java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.kenzie.mx/releases")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {

    api("org.python:jython-slim:2.7.3b1")
    api("mx.kenzie:mimic:1.1.0")
    //api("com.google.guava:guava:31.1-jre")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

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

