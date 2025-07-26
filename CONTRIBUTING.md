# Contributing to Dracula Theme for JetBrains IDEs

Thank you for your interest in contributing to the Dracula Theme! This document will help you get started with development and debugging.

## 🚀 Quick Start

1. **Clone the repository**

   ```bash
   git clone https://github.com/dracula/jetbrains.git
   cd jetbrains
   ```

2. **Import into IntelliJ IDEA**
   - Open IntelliJ IDEA Ultimate
   - Select "Open" or "Import Project"
   - Choose the cloned directory
   - Select "Import project from external model" → "Gradle"
   - Follow the import wizard

## 📋 Prerequisites

### Required Software

- **JDK 17** or later
- **Python 3.10** or later
- **IntelliJ IDEA Ultimate** 2024.1 or later

### Recommended Plugins

Install these IntelliJ IDEA plugins for comprehensive language support and debugging:

| Plugin     | Purpose                                         |
| ---------- | ----------------------------------------------- |
| **Scala**  | Scala language support and syntax highlighting  |
| **Python** | Python language support and syntax highlighting |
| **Ruby**   | Ruby language support and syntax highlighting   |
| **PHP**    | PHP language support and syntax highlighting    |
| **Go**     | Go language support and syntax highlighting     |

## 🛠️ Development Setup

### 1. Project Structure

```
dracula-theme/
├── src/main/
│   ├── kotlin/          # Plugin logic
│   └── resources/
│       ├── themes/      # Theme files (.xml, .theme.json)
│       └── META-INF/    # Plugin metadata
├── build.gradle.kts     # Build configuration
└── README.md
```

### 2. Theme Development

- **Color Schemes**: Located in `src/main/resources/themes/`
- **UI Themes**: Defined in `.theme.json` files
- **Editor Schemes**: Defined in `.xml` files

### 3. Building and Testing

```bash
# Run IDE with plugin for testing
./gradlew runIde

# Verify plugin compatibility
./gradlew verifyPlugin

# Create plugin distribution
./gradlew buildPlugin
```

## 🎨 Theme Customization

### Color Scheme Files

- `Dracula.xml` - Classic color scheme
- `DraculaColorful.xml` - Colorful variant
- `DraculaAlucard.xml` - Alucard variant

### UI Theme Files

- `Dracula.theme.json` - Classic UI theme
- `DraculaColorful.theme.json` - Colorful UI theme
- `DraculaAlucard.theme.json` - Alucard UI theme

### Key Color Properties

```json
{
  "colors": {
    "accentColor": "#ff79c6",
    "secondaryAccentColor": "#bd93f9",
    "primaryForeground": "#f8f8f2",
    "primaryBackground": "#414450",
    "selectionBackground": "#44475a"
  }
}
```

## 🐛 Debugging

### 1. Plugin Debugging

- Set breakpoints in Kotlin files
- Use IntelliJ's built-in debugger
- Check the IDE log for errors

### 2. Theme Debugging

- Use IntelliJ's "Settings" → "Editor" → "Color Scheme" to preview changes
- Test with different file types and languages
- Verify contrast ratios for accessibility

### 3. Common Issues

- **Theme not loading**: Check plugin.xml configuration
- **Colors not applying**: Verify color scheme inheritance
- **Build failures**: Ensure JDK 11+ is being used

## 📝 Making Changes

### 1. Create a Feature Branch

```bash
git checkout -b feature/your-feature-name
```

### 2. Make Your Changes

- Edit theme files in `src/main/resources/themes/`
- Update plugin logic in `src/main/kotlin/`
- Test thoroughly with different languages

### 3. Test Your Changes

- Build the plugin: `./gradlew buildPlugin`
- Install the plugin in a test IntelliJ instance
- Test with various file types and languages

### 4. Submit a Pull Request

- Fork the repository
- Push your changes to your fork
- Create a pull request with a clear description

## 📚 Resources

### Official Documentation

- **[IntelliJ Platform SDK](https://www.jetbrains.org/intellij/sdk/docs/reference_guide/ui_themes/themes_intro.html)** - Complete guide to theme development
- **[Gradle IntelliJ Plugin](https://github.com/JetBrains/gradle-intellij-plugin)** - Build system documentation

### Useful Links

- **[Dracula Theme](https://draculatheme.com/)** - Official Dracula theme website
- **[IntelliJ Platform Plugin SDK](https://plugins.jetbrains.com/docs/intellij/welcome.html)** - Plugin development guide

## 🤝 Code of Conduct

Please be respectful and inclusive when contributing. We welcome contributions from developers of all skill levels and backgrounds.

## 📄 License

By contributing to this project, you agree that your contributions will be licensed under the same license as the project.

---

**Need help?** Open an issue on GitHub or join our community discussions!
