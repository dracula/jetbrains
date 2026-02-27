# Repository Guidelines

## Project Structure & Module Organization
This repository is a Gradle-based JetBrains plugin project.
- `src/main/kotlin/com/draculatheme/jetbrains/`: Kotlin plugin logic (startup activity, listeners, settings, notifications).
- `src/main/resources/themes/`: Theme assets (`*.theme.json` UI themes, `*.xml` editor color schemes).
- `src/main/resources/META-INF/plugin.xml`: Plugin metadata and registration.
- `docs/screenshots/`: Documentation and marketplace images.
- `.github/workflows/build.yml`: CI build, release, and marketplace publish flow.

There is currently no dedicated `src/test` module; validation is primarily plugin verification plus manual IDE checks.

## Build, Test, and Development Commands
Use the Gradle wrapper from repo root:
- `./gradlew runIde`: Launch a sandbox IntelliJ instance with the plugin for local testing.
- `./gradlew buildPlugin`: Build distributable ZIP in `build/distributions/`.
- `./gradlew verifyPlugin`: Run JetBrains plugin compatibility checks.
- `./gradlew publishPlugin -Djetbrains.token=$JETBRAINS_TOKEN`: Publish to JetBrains Marketplace (release workflow).

CI uses JDK 21 (`.github/workflows/build.yml`); match that locally when possible.

## Coding Style & Naming Conventions
- Kotlin: 4-space indentation, standard Kotlin conventions, keep package names under `com.draculatheme.jetbrains`.
- Theme resources: keep variant naming aligned across files (example: `DraculaAlucard.theme.json` and `DraculaAlucard.xml`).
- JSON/XML theme edits should stay consistent with existing key naming and formatting to reduce noisy diffs.
- Keep changes focused: separate functional plugin logic updates from large palette/theme data edits where practical.

## Testing Guidelines
- Run `./gradlew verifyPlugin` before opening a PR.
- Validate theme changes in `./gradlew runIde` across multiple file types/languages.
- For UI fixes, include before/after screenshots (see `.github` templates).
- If you add logic-heavy Kotlin code, add tests under a new `src/test` module when feasible.

## Commit & Pull Request Guidelines
Recent history favors short, imperative subjects, often emoji-prefixed (examples: `🐛 Fix ...`, `🎨 Update ...`, `🔖 Bump version ...`).
- Keep commit messages specific to one logical change.
- Reference affected theme variant(s) or plugin component(s).
- PRs should include: clear description, linked issue (if any), testing notes (`runIde`/`verifyPlugin`), and screenshots for visual changes.
