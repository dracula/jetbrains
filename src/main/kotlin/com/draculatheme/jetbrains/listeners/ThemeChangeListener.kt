package com.draculatheme.jetbrains.listeners

import com.draculatheme.jetbrains.enums.DraculaVariant
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.editor.colors.EditorColorsManager

class ThemeChangeListener : LafManagerListener {

    private val editorColorSchemeManager = EditorColorsManager.getInstance()

    override fun lookAndFeelChanged(lafManager: LafManager) {
        when (val currentTheme = lafManager.currentLookAndFeel.name) {
            DraculaVariant.Dracula.label -> {
                editorColorSchemeManager.globalScheme = editorColorSchemeManager.getScheme("_@user_$currentTheme")
            }

            DraculaVariant.DraculaColorful.label -> {
                editorColorSchemeManager.globalScheme = editorColorSchemeManager.getScheme("_@user_$currentTheme")
            }
        }
    }
}
