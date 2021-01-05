import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.21"
    id("org.jetbrains.intellij") version "0.6.5"
}

group = "com.draculatheme"
version = "1.9.7"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

intellij {
    version = "LATEST-EAP-SNAPSHOT"
    type = "IC"
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    buildSearchableOptions {
        enabled = false
    }
    patchPluginXml {
        sinceBuild("203.3645.34")

        val changelogFile = file("${project.buildDir}/CHANGELOG.html")
        val readmeFile = file("${project.buildDir}/README.html")
        if (changelogFile.exists()) {
            changeNotes(changelogFile.readText())
        }
        if (readmeFile.exists()) {
            pluginDescription(readmeFile.readText())
        }
    }
    publishPlugin {
        token(System.getProperty("jetbrains.token"))
    }
}