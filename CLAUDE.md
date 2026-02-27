# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
./gradlew buildPlugin       # Build distributable plugin zip
./gradlew runIde            # Launch a sandboxed IDE instance with the plugin loaded
./gradlew verifyPlugin      # Run plugin verifier for compatibility checks
./gradlew publishPlugin -Djetbrains.token=<TOKEN>  # Publish to JetBrains Marketplace
```

## Architecture

This is an IntelliJ Platform plugin (plugin ID: `com.vermouthx.idea`) that provides 6 Dracula color theme variants. There is no test suite.

### Theme variants

Three base variants and three "Islands" variants that wrap a JetBrains built-in parent theme:

| Variant | Parent | Editor scheme |
|---|---|---|
| Dracula | — | `Dracula.xml` |
| Dracula Colorful | — | `DraculaColorful.xml` |
| Dracula Alucard | — | `DraculaAlucard.xml` |
| Islands Dracula | Islands Dark | `Dracula.xml` (reused) |
| Islands Dracula Colorful | Islands Dark | `DraculaColorful.xml` (reused) |
| Islands Dracula Alucard | Islands Dark | `DraculaAlucard.xml` (reused) |

### Theme file structure

Each theme consists of two files in `src/main/resources/themes/`:

- **`*.theme.json`** — UI theme: named color palette under `colors`, component overrides under `ui`, icon color palette under `icons`. Islands variants add `"parentTheme": "Islands Dark"`.
- **`*.xml`** — Editor color scheme (syntax highlighting, gutter colors, etc.)

Colors in `.theme.json` are first defined in the `colors` block as named tokens (e.g., `accentColor`), then referenced by name in `ui`. Hard-coded hex values are also allowed where the named palette doesn't apply.

### Kotlin source

All source lives in `src/main/kotlin/com/draculatheme/jetbrains/`:

- **`DraculaMeta.kt`** — exposes `currentVersion` read from the plugin descriptor
- **`enums/DraculaVariant.kt`** — enum of all 6 variants with their display labels; used as the authoritative list throughout the plugin
- **`settings/DraculaSettings.kt` + `DraculaState.kt`** — persistent app-level settings (currently stores the last-seen version to detect upgrades)
- **`activities/DraculaStartupActivity.kt`** — runs on project open; compares stored version to current, fires install or upgrade notification
- **`listeners/DraculaThemeChangeListener.kt`** — listens to `LafManagerListener`; when an Islands variant is selected, maps it to the corresponding base editor scheme (`_@user_<SchemeName>`) because Islands variants share editor scheme XML files with their base counterparts
- **`notifications/DraculaNotification.kt`** — renders styled HTML notifications for first install and version upgrades

### Release workflow

Releases are triggered by pushing a `v1.*` tag. CI (`build.yml`) builds the plugin, creates a GitHub Release with the zip artifact, then publishes to the JetBrains Marketplace using the `JETBRAINS_TOKEN` secret.

To release a new version:
1. Update `pluginVersion` in `gradle.properties`
2. Add an entry to `CHANGELOG.md` (the latest section is used as marketplace change notes)
3. Update `whatsNew` HTML in `DraculaNotification.kt`
4. Commit, tag `v1.x.y`, and push the tag
