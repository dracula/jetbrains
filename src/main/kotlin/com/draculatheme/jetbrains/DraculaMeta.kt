package com.draculatheme.jetbrains

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId

object DraculaMeta {
    private const val PLUGIN_ID = "com.vermouthx.idea"

    val currentVersion: String
        get() = PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID))?.version ?: ""

}