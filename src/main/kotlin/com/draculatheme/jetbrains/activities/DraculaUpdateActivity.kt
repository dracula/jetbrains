package com.draculatheme.jetbrains.activities

import com.draculatheme.jetbrains.DraculaMeta
import com.draculatheme.jetbrains.notifications.DraculaNotifications
import com.draculatheme.jetbrains.settings.DraculaSettings
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class DraculaUpdateActivity : StartupActivity, DumbAware {
    override fun runActivity(project: Project) {
        val settings = DraculaSettings.instance
        val updated = DraculaMeta.currentVersion != settings.version
        if (updated) {
            settings.version = DraculaMeta.currentVersion
            DraculaNotifications.notifyReleaseNote(project)
        }
    }
}