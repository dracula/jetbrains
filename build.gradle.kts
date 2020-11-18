import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.intellij") version "0.4.22"
    id("org.kordamp.gradle.markdown") version "2.2.0"
}

group = "com.draculatheme"
version = "1.9.1"

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

listOf("compileKotlin", "compileTestKotlin").forEach {
    tasks.getByName<KotlinCompile>(it) {
        kotlinOptions.jvmTarget = "1.8"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

intellij {
    version = "2020.2"
    type = "IC"
}

tasks {
    patchPluginXml {
        sinceBuild("202")
        untilBuild("203.*")

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