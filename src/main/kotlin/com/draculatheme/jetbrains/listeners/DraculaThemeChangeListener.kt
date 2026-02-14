package com.draculatheme.jetbrains.listeners

import com.draculatheme.jetbrains.enums.DraculaVariant
import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.editor.colors.EditorColorsManager

class DraculaThemeChangeListener : LafManagerListener {

    private val editorColorsManager = EditorColorsManager.getInstance()

    private var previousUI = LafManager.getInstance().currentUIThemeLookAndFeel.name

    override fun lookAndFeelChanged(lafManager: LafManager) {
        val currentUI = lafManager.currentUIThemeLookAndFeel.name
        if (previousUI != currentUI) {
            val draculaThemes = DraculaVariant.entries.map { it.label }
            if (currentUI in draculaThemes) {
                val schemeName = when (currentUI) {
                    DraculaVariant.IslandsDracula.label -> DraculaVariant.Dracula.label
                    DraculaVariant.IslandsDraculaColorful.label -> DraculaVariant.DraculaColorful.label
                    DraculaVariant.IslandsDraculaAlucard.label -> DraculaVariant.DraculaAlucard.label
                    else -> currentUI
                }
                editorColorsManager.setGlobalScheme(editorColorsManager.getScheme("_@user_$schemeName"))
            }
        }
        previousUI = currentUI
    }

}
