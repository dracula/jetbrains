import org.jetbrains.intellij.tasks.RunPluginVerifierTask

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.9.0"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "1.3.1"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    updateSinceUntilBuild.set(false)
}

changelog {
    version.set(properties("pluginVersion"))
    path.set("${project.projectDir}/CHANGELOG.md")
    groups.set(emptyList())
}

tasks {
    buildSearchableOptions {
        enabled = false
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))

        val description = """
            <div>
              <p>Dracula Theme for JetBrains</p>
              <br />
              <p>
                <img
                  alt="Screenshot"
                  src="https://raw.githubusercontent.com/dracula/jetbrains/master/screenshot.png"
                  width="600"
                />
              </p>
              <h2>Install</h2>
              <p>
                All instructions can be found at
                <a href="https://draculatheme.com/jetbrains">draculatheme.com/jetbrains</a>.
              </p>
              <h2>Dracula PRO</h2>
              <p>
                <a href="https://gumroad.com/a/477820019"
                  ><img
                    alt="Dracula Pro"
                    src="https://raw.githubusercontent.com/dracula/jetbrains/master/docs/screenshots/dracula-pro.png"
                /></a>
              </p>
              <p>
                Dracula PRO is a color scheme and UI theme tailored for programming. Made
                for terminal emulators, code editors, and syntax highlighters. Designed to
                be aesthetically pleasing while keeping you focused.
              </p>
              <p><a href="https://gumroad.com/a/477820019">Get it now</a></p>
              <h2>Licence</h2>
              <p>
                <a href="https://raw.githubusercontent.com/dracula/jetbrains/master/LICENSE"
                  >MIT Licence</a
                >
              </p>
            </div>
        """.trimIndent()

        pluginDescription.set(description)
        changeNotes.set(provider { changelog.getLatest().toHTML() })
    }

    runPluginVerifier {
        ideVersions.set(
            properties("pluginVerifierIdeVersions")
                .split(",")
                .map(String::trim)
                .filter(String::isNotEmpty)
        )
        failureLevel.set(
            listOf(
                RunPluginVerifierTask.FailureLevel.COMPATIBILITY_PROBLEMS,
                RunPluginVerifierTask.FailureLevel.INVALID_PLUGIN
            )
        )
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getProperty("jetbrains.token"))
    }
}
