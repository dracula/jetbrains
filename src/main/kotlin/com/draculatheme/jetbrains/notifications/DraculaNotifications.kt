package com.draculatheme.jetbrains.notifications

import com.draculatheme.jetbrains.DraculaMeta
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import org.intellij.lang.annotations.Language

object DraculaNotifications {

    private val NOTIFICATION_GROUP = NotificationGroup("Dracula Theme", NotificationDisplayType.STICKY_BALLOON, true)

    @Language("HTML")
    private val whatsNew = """
        <ul>
            <li>Fixed Android Studio compatibility</li>
            <li>Enhanced VCS Editor Gutter colors</li>
        </ul>
    """.trimIndent()

    @Language("HTML")
    private val linkMessage = """
        <p><a href="https://github.com/dracula/jetbrains/blob/master/CHANGELOG.md">Changelog</a> | <a href="https://gumroad.com/a/477820019">Dracula PRO</a> | <a href="https://github.com/dracula/jetbrains">Star Repository</a></p>
    """.trimIndent()

    @Language("HTML")
    private val releaseNote = """
        <div>
            <h3>What's New</h3>
            $whatsNew
            <p>Thank you for choosing Dracula.</p>
            $linkMessage
        </div>
    """.trimIndent()

    @Language("HTML")
    private val welcomeMessage = """
        <div>
            <p>Thank you for choosing Dracula.</p>
            $linkMessage
        </div>
    """.trimIndent()

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    fun notifyReleaseNote(project: Project) {
        val title = "Dracula Theme updated to v${DraculaMeta.currentVersion}"
        val notification = NOTIFICATION_GROUP.createNotification(
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
        val notification = NOTIFICATION_GROUP.createNotification(
            title,
            welcomeMessage,
            NotificationType.INFORMATION,
            NotificationListener.URL_OPENING_LISTENER
        )
        notification.icon = notificationIcon
        notification.notify(project)
    }

}