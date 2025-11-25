import org.jetbrains.changelog.Changelog
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease
import org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.2.1"
    id("org.jetbrains.changelog") version "2.2.1"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        create(properties("platformType"), properties("platformVersion"))
        pluginVerifier()
    }
}

changelog {
    version.set(properties("pluginVersion"))
    path.set("${project.projectDir}/CHANGELOG.md")
    groups.set(emptyList())
}

val pluginDescription = """
    <div>
      <p>Dracula Theme for JetBrains</p>
      <h2>Install</h2>
      <p>
        All instructions can be found at
        <a href="https://draculatheme.com/jetbrains">draculatheme.com/jetbrains</a>.
      </p>
      <h2>Dracula PRO</h2>
      <p>
        <a href="https://gumroad.com/a/477820019">
          <img
            alt="Dracula Pro"
            src="https://raw.githubusercontent.com/dracula/jetbrains/master/docs/screenshots/dracula-pro.png"
          />
        </a>
      </p>
      <p>
        Dracula PRO is a color scheme and UI theme tailored for programming. Made
        for terminal emulators, code editors, and syntax highlighters. Designed to
        be aesthetically pleasing while keeping you focused.
      </p>
      <p><a href="https://gumroad.com/a/477820019">Get it now</a></p>
      <h2>Licence</h2>
      <p>
        <a href="https://raw.githubusercontent.com/dracula/jetbrains/master/LICENSE">
          MIT Licence
        </a>
      </p>
      <h2>Donation</h2>
      <p>
        If you like this plugin, you can
        <a href="https://www.buymeacoffee.com/nszihan">buy me a cup of coffee</a>.
        Thank you!
      </p>
    </div>
""".trimIndent()

intellijPlatform {
    buildSearchableOptions = false
    pluginConfiguration {
        name = properties("pluginName")
        version = properties("pluginVersion")
        description = pluginDescription
        changeNotes = provider {
            changelog.renderItem(changelog.getLatest(), Changelog.OutputType.HTML)
        }
        ideaVersion {
            untilBuild = provider { null }
        }
    }
    publishing {
        token = System.getProperty("jetbrains.token")
    }
    pluginVerification {
        ides {
            recommended()
            select {
                types = listOf(IntelliJPlatformType.IntellijIdeaCommunity)
                channels = listOf(ProductRelease.Channel.RELEASE)
                sinceBuild = "241"
                untilBuild = "252.*"
            }
        }
        failureLevel = listOf(
            VerifyPluginTask.FailureLevel.COMPATIBILITY_PROBLEMS, VerifyPluginTask.FailureLevel.INVALID_PLUGIN
        )
    }
}
