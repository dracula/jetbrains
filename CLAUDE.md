# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
./gradlew buildPlugin       # Build distributable plugin zip
./gradlew runIde            # Launch a sandboxed IDE instance with the plugin loaded
./gradlew verifyPlugin      # Run plugin verifier for compatibility checks
JETBRAINS_TOKEN=<TOKEN> ./gradlew publishPlugin    # Publish to JetBrains Marketplace
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
| Islands Dracula Alucard | Islands **Light** | `DraculaAlucard.xml` (reused) |

Alucard is the light variant, so its Islands counterpart parents off Islands Light, not Islands Dark.

### Theme file structure

Each theme consists of two files in `src/main/resources/themes/`:

- **`*.theme.json`** — UI theme: named color palette under `colors`, component overrides under `ui`, icon color palette under `icons`. Each declares its editor scheme via the `editorScheme` key. Islands variants add `"parentTheme": "Islands Dark"` and point `editorScheme` at the reused base XML (e.g. `IslandsDracula.theme.json` → `/themes/Dracula.xml`).
- **`*.xml`** — Editor color scheme (syntax highlighting, gutter colors, etc.)

Colors in `.theme.json` are first defined in the `colors` block as named tokens (e.g., `accentColor`), then referenced by name in `ui`. Hard-coded hex values are also allowed where the named palette doesn't apply.

### Kotlin source

All source lives in `src/main/kotlin/com/draculatheme/jetbrains/`:

- **`DraculaMeta.kt`** — exposes `currentVersion` read from the plugin descriptor; empty string if the lookup fails
- **`settings/DraculaSettings.kt` + `DraculaState.kt`** — persistent app-level settings (currently stores the last-seen version to detect upgrades). `exchangeVersion` is the only writer and is atomic, because the caller runs once per open project
- **`activities/DraculaStartupActivity.kt`** — runs on project open; compares stored version to current, fires install or upgrade notification
- **`notifications/DraculaNotification.kt`** — renders styled HTML notifications for first install and version upgrades

Islands variants share editor scheme XML with their base counterparts via the `editorScheme` key in their `.theme.json` (see [Theme file structure](#theme-file-structure)); there is no runtime listener performing the mapping.

### Release workflow

Every push and PR runs `buildPlugin` then `verifyPlugin`. Releases are triggered by pushing any `v*` tag: the release job builds once, attaches that zip to a GitHub Release, and publishes the same zip to the JetBrains Marketplace using the `JETBRAINS_TOKEN` secret.

To release a new version:
1. Update `pluginVersion` in `gradle.properties`
2. Add an entry to `CHANGELOG.md` (the latest section is used as marketplace change notes)
3. Update `whatsNew` HTML in `DraculaNotification.kt`
4. Commit, tag `vX.Y.Z`, and push the tag

User-facing release notes (`CHANGELOG.md` entries and `whatsNew` HTML) must describe the visible change and the user benefit. Do not mention implementation details — specific UI theme keys, hex values, refactor mechanics, or which canonical recipe was followed. Users see *what improved for them*, not *how it was wired up*.
