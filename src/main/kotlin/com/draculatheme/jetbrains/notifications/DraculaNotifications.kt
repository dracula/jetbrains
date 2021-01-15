package com.draculatheme.jetbrains.notifications

import com.draculatheme.jetbrains.DraculaMeta
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader

object DraculaNotifications {
    private const val notificationGroupID: String = "dracula-theme"

    private val releaseNote = """
        What's New?<br>
        <ul>
            <li>Regressed checkbox foreground</li>
            <li>Pick a lighter color for separator</li>
        </ul>
        Please visit the <a href="https://github.com/dracula/jetbrains/blob/master/CHANGELOG.md">Changelog</a> for more details.<br>
        For premium package, check out <a href="https://gumroad.com/a/477820019">Dracula PRO</a>.<br>
        Enjoy this theme? Consider <a href="https://github.com/dracula/jetbrains">STAR</a> this project.<br>
        Thank you for choosing Dracula.
    """.trimIndent()

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    fun notifyReleaseNote(project: Project) {
        NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupID)
            .createNotification(
                title = "Dracula Theme updated to v${DraculaMeta.currentVersion}",
                content = releaseNote,
                type = NotificationType.INFORMATION,
                listener = NotificationListener.URL_OPENING_LISTENER
            )
            .setIcon(notificationIcon)
            .notify(project)
    }

    fun notifyFirstlyDownloaded(project: Project) {
        NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupID)
            .createNotification(
                title = "Dracula Theme v${DraculaMeta.currentVersion} is installed",
                content = """
                    Dracula Theme is fully open-source. If this theme is helpful to you, consider <a href="https://github.com/dracula/jetbrains">STAR</a> this project. 
                    Thank you for choosing Dracula.
                """.trimIndent(),
                type = NotificationType.INFORMATION,
                listener = NotificationListener.URL_OPENING_LISTENER
            )
            .setIcon(notificationIcon)
            .notify(project)
    }

}