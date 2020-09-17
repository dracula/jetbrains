plugins {
    id("org.jetbrains.intellij") version "0.4.22"
}

repositories {
    mavenCentral()
}

group = "com.vermouthx"
version = "1.8.2"

intellij {
    version = "2020.1"
}

tasks {
    patchPluginXml {
        sinceBuild("193")
        untilBuild("202.*")

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
        username(System.getProperty("jetbrains.username"))
        token(System.getProperty("jetbrains.token"))
    }
}