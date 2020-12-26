package com.draculatheme.jetbrains.notifications

import com.draculatheme.jetbrains.DraculaMeta
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader

object DraculaNotifications {
    private val releaseNote = """
        What's New?<br>
        <ul>
            <li>Regressed in code inactive hyperlink color</li>
        </ul>
        Please visit the <a href="https://github.com/dracula/jetbrains/blob/master/CHANGELOG.md">Changelog</a> for more details.<br>
        For premium package, please explore <a href="https://gumroad.com/a/477820019">Dracula PRO</a>.<br>
        Thank you for choosing Dracula.
    """.trimIndent()

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    fun notifyReleaseNote(project: Project) {
        NotificationGroupManager.getInstance().getNotificationGroup("dracula-theme")
            .createNotification(
                title = "Dracula Theme updated to v${DraculaMeta.currentVersion}",
                content = releaseNote,
                type = NotificationType.INFORMATION,
                listener = NotificationListener.URL_OPENING_LISTENER
            )
            .setIcon(notificationIcon)
            .notify(project)
    }

}