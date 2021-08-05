package com.qngsontheme.jetbrains.activities

import com.qngsontheme.jetbrains.DraculaMeta
import com.qngsontheme.jetbrains.notifications.DraculaNotifications
import com.qngsontheme.jetbrains.settings.DraculaSettings
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class DraculaStartupActivity : StartupActivity, DumbAware {
    override fun runActivity(project: Project) {
        val settings = DraculaSettings.instance
        if (settings.version.isEmpty()) {
            settings.version = DraculaMeta.currentVersion
            DraculaNotifications.notifyFirstlyDownloaded(project)
            return
        }
        if (DraculaMeta.currentVersion != settings.version) {
            settings.version = DraculaMeta.currentVersion
            DraculaNotifications.notifyReleaseNote(project)
        }
    }
}