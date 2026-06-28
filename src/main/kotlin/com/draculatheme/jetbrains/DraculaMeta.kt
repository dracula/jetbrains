package com.draculatheme.jetbrains

import com.intellij.ide.plugins.cl.PluginAwareClassLoader

object DraculaMeta {
    // The plugin's own classes are loaded by a PluginAwareClassLoader, which
    // exposes the plugin descriptor (and its version) directly — no need for the
    // internal PluginManager lookup API.
    val currentVersion: String
        get() = (DraculaMeta::class.java.classLoader as? PluginAwareClassLoader)
            ?.pluginDescriptor?.version ?: ""
}
