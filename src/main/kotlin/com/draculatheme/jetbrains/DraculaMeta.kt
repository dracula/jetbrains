package com.draculatheme.jetbrains

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId

object DraculaMeta {
    val currentVersion: String
        get() = PluginManagerCore.findPlugin(PluginId.getId("com.vermouthx.idea"))?.version ?: ""
}