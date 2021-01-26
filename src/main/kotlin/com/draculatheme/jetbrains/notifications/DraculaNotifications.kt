package com.draculatheme.jetbrains.notifications

import com.draculatheme.jetbrains.DraculaMeta
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import org.intellij.lang.annotations.Language

object DraculaNotifications {
    private const val notificationGroupID: String = "dracula-theme"

    @Language("HTML")
    private val whatsNew = """
        <ul>
            <li>Fixed selection foreground</li>
            <li>Fixed <em>Dracula</em> text selection background color</li>
            <li>Enhanced Console ANSI and Log colors</li>
        </ul>
    """.trimIndent()

    @Language("HTML")
    private val releaseNote = """
        <div>
            <h3>What's New</h3>
            $whatsNew
            <p>Please visit the <a href="https://github.com/dracula/jetbrains/blob/master/CHANGELOG.md">Changelog</a> for more details.</p>
            <p>For premium package, check out <a href="https://gumroad.com/a/477820019">Dracula PRO</a>.</p>
            <p>Enjoy this theme? Consider <a href="https://github.com/dracula/jetbrains">STAR</a> this project.</p>
            <p>Thank you for choosing Dracula.</p>
        </div>
    """.trimIndent()

    @Language("HTML")
    private val welcomeMessage = """
        <div>
            <p>Thank you for choosing Dracula.</p>
            <p>Dracula Theme is fully open-source. If you enjoy this theme, consider <a href="https://github.com/dracula/jetbrains">STAR</a> this project.</p>
        </div>
    """.trimIndent()

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.png", javaClass)

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
                content = welcomeMessage,
                type = NotificationType.INFORMATION,
                listener = NotificationListener.URL_OPENING_LISTENER
            )
            .setIcon(notificationIcon)
            .notify(project)
    }

}