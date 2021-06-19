package com.draculatheme.jetbrains.notifications

import com.draculatheme.jetbrains.DraculaMeta
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import org.intellij.lang.annotations.Language

object DraculaNotifications {

    @Language("HTML")
    private val whatsNew = """
        <ul>
            <li>Support IntelliJ 2021.2 EAP</li>
        </ul>
    """.trimIndent()

    @Language("HTML")
    private val linkMessage = """
        <p>
            <a href="https://github.com/dracula/jetbrains/blob/master/CHANGELOG.md">Changelog</a> | 
            <a href="https://gumroad.com/a/477820019">Dracula PRO</a> | 
            <a href="https://github.com/dracula/jetbrains">Star Repository</a>
        </p>
    """.trimIndent()

    @Language("HTML")
    private val releaseNote = """
        <div>
            <h3>What's New</h3>
            $whatsNew
            <p>Thank you for choosing Dracula.</p>
            <br>
            $linkMessage
        </div>
    """.trimIndent()

    @Language("HTML")
    private val welcomeMessage = """
        <div>
            <p>Thank you for choosing Dracula.</p>
            <br>
            $linkMessage
        </div>
    """.trimIndent()

    private const val notificationGroupId = "Dracula Theme"

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    fun notifyReleaseNote(project: Project) {
        val title = "Dracula Theme updated to v${DraculaMeta.currentVersion}"
        val notification =
            NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId).createNotification(
                title,
                releaseNote,
                NotificationType.INFORMATION,
                NotificationListener.URL_OPENING_LISTENER
            )
        notification.icon = notificationIcon
        notification.notify(project)
    }

    fun notifyFirstlyDownloaded(project: Project) {
        val title = "Dracula Theme v${DraculaMeta.currentVersion} installed"
        val notification =
            NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId).createNotification(
                title,
                welcomeMessage,
                NotificationType.INFORMATION,
                NotificationListener.URL_OPENING_LISTENER
            )
        notification.icon = notificationIcon
        notification.notify(project)
    }

}