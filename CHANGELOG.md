# Changelog

## 1.18.8

- Refine Islands theme border styles: distinct focus ring, visible ActionButton borders, StatusBar separator, and neutral Button borders

## 1.18.7

- Fix theme change listener for Islands variants

## 1.18.6

- Fix invisible UI accents in Alucard themes by correcting `secondaryAccentColor` from background color to Alucard Purple
- Adopt spec Functional Colors for UI state indicators (error, warning, success, info) across both Alucard theme variants
- Fix editor selection background and ANSI console white output to match Alucard spec
- Add italic styling to type parameters per spec Generic Templates rule
- Add proper non-Dark checkbox icon variants for light theme rendering
- Soften search match highlight and fix bookmark mnemonic foreground visibility

## 1.18.5

- Add Rust syntax highlighting support for RustRover 2025.2 compatibility [#100](https://github.com/dracula/jetbrains/issues/100)

## 1.18.4

- Fix main window background color inconsistency in Islands Dracula variants [#106](https://github.com/dracula/jetbrains/issues/106)

## 1.18.3

- Fix inconsistent background color for local variable write occurrences in Dracula and Dracula Colorful themes

## 1.18.2

- Improve notification card background contrast in Islands themes for better visibility

## 1.18.1

- Correct selected-state background styling for checkboxes and radio items
- Refine the MemoryIndicator visuals for clearer status feedback

## 1.18.0

- Add Islands Dracula, Islands Dracula Colorful, and Islands Dracula Alucard themes
- Update minimum IntelliJ version to 2025.3

## 1.17.5

- Improve breakpoint line readability in Dracula Alucard theme [#103](https://github.com/dracula/jetbrains/issues/103)

## 1.17.4

- Improve identifier under caret background color for better visibility
- Update selection background to neutral gray for better contrast with identifier highlighting

## 1.17.3

- Improve text selection contrast to make selected text more distinguishable [#101](https://github.com/dracula/jetbrains/issues/101)

## 1.17.2

- Fix caret row color for better reading with selection background color

## 1.17.1

- Pick a better color for editor selection background [#82](https://github.com/dracula/jetbrains/issues/82)

## 1.17.0

- Add a new Light Theme: Alucard. Thanks [@epszaw](https://github.com/epszaw)

## 1.16.0

- Fix compatibility issue

## 1.15.2

- Force switch to correspond editor color when toggle UI to Dracula

## 1.15.1

- Workaround for VCS annotation background color lost
- Revert Tool Window selected background

## 1.15.0

- Fix new notification window colors
- Fix tool window button selected background color

## 1.14.3

- Fix hash color in Tree View

## 1.14.2

- Fix notification message layout & typo

## 1.14.1

- Fix welcome window action button background color

## 1.14.0

- Fix welcome window project color

## 1.13.3

- Fix variable occurrences background color

## 1.13.2

- Pick less disruptive indent-guide color
- Fix identifier-under-caret error-stripe-mark color

## 1.13.1

- Fix compatibility issue

## 1.13.0

- Support JetBrains 2022 EAP

## 1.12.4

- Support Color of Mnemonic Bookmark [#57](https://github.com/dracula/jetbrains/issues/57)

## 1.12.3

- Fixed Bookmark Icon Background [#66](https://github.com/dracula/jetbrains/issues/66)

## 1.12.2

- Support JetBrains 2021.3 series

## 1.12.1

- Fixed Completion Popup background

## 1.12.0

- Fixed API compatibility

## 1.11.5

- Support IntelliJ 2021.2 EAP

## 1.11.4

- Fixed ValidationTooltip background [#50](https://github.com/dracula/jetbrains/issues/50)

## 1.11.3

- Fixed Android Studio compatibility
- Enhanced VCS Editor Gutter colors

## 1.11.2

- Enhanced Go color scheme
- Removed JetBrains 2019 series support

## 1.11.1

- Tweak `Matched brace` style

## 1.11.0

- Add Logcat console coloration
- Tine editor scrollbar (macOS only)
- Pick a lighter color for editor line number
- Enhance Diff & Merge background colors
- Support JetBrains 2019 series (2019.2 at least)

## 1.10.6

- Support older JetBrains IDE(2020.1 at least)

## 1.10.5

- Support JetBrains IDE 2021

## 1.10.4

- Fixed ComboBox selection foreground
- Fixed `Dracula` text selection background color
- Enhanced Console ANSI and Log colors

## 1.10.3

- Enhanced File background color. See issue [#36](https://github.com/dracula/jetbrains/issues/36) for more details
- Enhanced `Dracula` editor caret background and selection background. See
  issue [#37](https://github.com/dracula/jetbrains/issues/37) for more details
- Revised editor popup background. See issue [#38](https://github.com/dracula/jetbrains/issues/38) for more details
- Regressed Italic function parameters and decorators. See issue [#39](https://github.com/dracula/jetbrains/issues/39)
  for more details

## 1.10.2

- Regressed checkbox foreground
- Pick a lighter color for separator

## 1.10.1

- Enhanced a bunch of UI components:
  - Button
  - ToolWindow Bar
  - Editor Bar
  - SidePanel
  - Checkbox
  - ComboBox
  - Separator

## 1.10.0

- Enhanced UI details of:
  - SidePanel
  - Welcome window

## 1.9.6

- Regressed in code inactive hyperlink color

## 1.9.5

- Enhanced scrollbar symbol colors. [See Issue #33](https://github.com/dracula/jetbrains/issues/33)
- Enhanced other colors inside General scheme.

## 1.9.4

- Make TODO comment distinguishable

## 1.9.3

- Refine Table UI color
- Fix Plugins UI hover selection color
- Fix Commit UI hover selection color

## 1.9.2

- Enhanced Python color scheme
- Enhanced General color scheme
- Enhanced Progress Bar

## 1.9.1

- Adopt a new plugin icon

## 1.9.0

- Enhanced color schemes
- Enhanced file status colors

## 1.8.3

- Fix 2020.3 compatibility issue
- Dracula
  - Use raw comment color

## 1.8.2

- Dracula Colorful
  - Enhanced file status colors

## 1.8.1

- Add a new appearance and color scheme: Dracula Colorful

## 1.8.0

- Try to make the color scheme conform to
  the [Dracula Syntax Highlighting Specification](https://spec.draculatheme.com/)

## 1.7.2

- Fix some error color schemes

## 1.7.1

- Optimize Swift/OC color scheme

## 1.7.0

- Fix IDE 2020.2 compatibility issue

## 1.6.3

- Improve Language Default color scheme
- Add ScrollBar color (only for macOS)
- Use thinner Tab Underline

## 1.6.2

- Fix Kotlin color scheme
- Fix UDF File Type color scheme

## 1.6.1

- Improve Groovy color scheme
- Improve Language Default

## 1.6.0

- Add support for 2020.1 EAP
- Fix PHP variable and parameter color

## 1.5.3

- Remove scrollbar colors
- Improve color scheme

## 1.5.2

- Optimize editor caret color
- Refine Kotlin color scheme
- Fix Ruby color scheme

## 1.5.1

- Fix Golang color scheme

## 1.5.0

- Support 2019.3 IDEs
- Refine some Color Schemes
- Fix color losing in some languages

## 1.4.9

- Add support for IDEs 2019.3

## 1.4.8

- refine Color Scheme
- regress EditorTab underline

## 1.4.7

- optimize Language Default
- optimize Jupyter
- colorize scrollbar

## 1.4.6

- improve Breadcrumbs
- fix Ruby String Block
- remove EditorTab underline

## 1.4.5

- improve PHP support
- refine Border and Tab color
- darker Separator Lines in Editor
- optimize Guide Lines

## 1.4.4

- improve text selection in Editor
- improve text foreground
- improve Editor Breadcrumbs and Separator Lines

## 1.4.3

- optimize inactive selection background

## 1.4.2

- new darker Blue and Green Icon
- new Editor background when all tabs closed
- new Gutter background
- add semantic highlighting (enable it manually in Color Scheme)
- fix underline style

## 1.4.1

- support Go Language
- optimize JavaScript Color Scheme
- optimize HTML Color Scheme
- optimize HTTP Request Color Scheme
- optimize Groovy Color Scheme
- optimize Kotlin Color Scheme
- optimize Dockerfile Color Scheme
- fix TabbedPane color

## 1.4.0

- built for 2019.2
- new Tab underline style

## 1.3.15

- fix 2019.2 compatibility issue

## 1.3.14

- optimize Ruby Scheme
- colorful braces

## 1.3.13

- new color for local variable and method parameters

## 1.3.12

- optimize Java Scheme
- optimize YAML Scheme
- optimize XML Scheme
- optimize JSON Scheme

## 1.3.11

- optimize Python Scheme

## 1.3.10

- fix color of comment

## 1.3.9

- some minor improvement

## 1.3.8

- optimize Plugin Install Button Border color
- optimize ComboBox and Checkbox color
- optimize VCS window color

## 1.3.7

- refine Popup Header color
- refine Button color
- refine Plugins color

## 1.3.6

- fix Component focusColor

## 1.3.5

- fix ToolWindow Header

## 1.3.4

- refine SearchEverywhere

## 1.3.3

- add border color
- refine Plugin dialog

## 1.3.2

- refine ProgressBar trackColor
- refine Label infoForeground

## 1.3.1

- refine ComboBox
- refine Component
- refine Notification
- fix infoForeground
- fix ProgressBar
- fix Label

## 1.3.0

- refine ComboBox color
- refine Notification color
- refine WelcomeScreen color
- redesign Object icon color and Tree selection color
- fix VCS related color in Color Scheme
- fix CheckBox border color
- some other refinement

## 1.2.4

- make editor section more clear when all files are closed

## 1.2.3

- fix the ProgressBar trackColor
- fix Plugin update Button
- optimize Notification
- optimize selectionBackground

## 1.2.2

- replace original ActionGrey color with white color to avoid color shadowing in some dialogs

## 1.2.1

- fix WelcomeScreen button hover color
- optimize Button color
- optimize CompletionPopup color
- optimize ProgressBar color
- some other minor refinement

## 1.2.0

- optimize Button color
- optimize ToggleButton color
- fix ActionButton
- fix Popup
- fix Editor shortcutForeground
- fix SearchMatch
- fix SearchEverywhere

## 1.1.1

- optimize plugin dialog
- optimize selection color
- some other refinement

## 1.1.0

- optimize button color
- override some default color with dracula palette
- some other visual optimization

## 1.0.0

- this theme is not completely conform the specification of dracula-theme
