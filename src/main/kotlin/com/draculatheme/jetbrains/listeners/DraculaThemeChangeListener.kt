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
            if (currentUI == DraculaVariant.Dracula.label || currentUI == DraculaVariant.DraculaColorful.label || currentUI == DraculaVariant.DraculaAlucard.label) {
                editorColorsManager.setGlobalScheme(editorColorsManager.getScheme("_@user_$currentUI"))
            }
        }
        previousUI = currentUI
    }

}
