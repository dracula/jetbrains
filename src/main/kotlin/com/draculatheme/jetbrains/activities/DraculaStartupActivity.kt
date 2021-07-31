package com.draculatheme.jetbrains.activities

import com.draculatheme.jetbrains.DraculaMeta
import com.draculatheme.jetbrains.notifications.DraculaNotification
import com.draculatheme.jetbrains.settings.DraculaSettings
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class DraculaStartupActivity : StartupActivity, DumbAware {
    override fun runActivity(project: Project) {
        val settings = DraculaSettings.instance
        if (settings.version.isEmpty()) {
            settings.version = DraculaMeta.currentVersion
            DraculaNotification.notifyFirstlyDownloaded(project)
            return
        }
        if (DraculaMeta.currentVersion != settings.version) {
            settings.version = DraculaMeta.currentVersion
            DraculaNotification.notifyReleaseNote(project)
        }
    }
}