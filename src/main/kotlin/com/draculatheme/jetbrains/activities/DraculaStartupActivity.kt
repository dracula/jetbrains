package com.draculatheme.jetbrains.activities

import com.draculatheme.jetbrains.DraculaMeta
import com.draculatheme.jetbrains.notifications.DraculaNotification
import com.draculatheme.jetbrains.settings.DraculaSettings
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class DraculaStartupActivity : ProjectActivity, DumbAware {
    override suspend fun execute(project: Project) {
        val currentVersion = DraculaMeta.currentVersion
        // An empty string means the descriptor lookup failed, and it is also the "never seen
        // before" sentinel. Storing it would leave the sentinel in place and re-post the
        // install balloon on every project open, so leave the stored version untouched.
        if (currentVersion.isEmpty()) return

        // Null means another project already recorded this version, so it has notified for us.
        val previousVersion = DraculaSettings.instance.exchangeVersion(currentVersion) ?: return

        if (previousVersion.isEmpty()) {
            DraculaNotification.notifyFirstlyDownloaded(project)
        } else {
            DraculaNotification.notifyReleaseNote(project)
        }
    }
}
