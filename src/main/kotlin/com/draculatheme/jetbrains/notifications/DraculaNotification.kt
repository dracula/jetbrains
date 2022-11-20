package com.draculatheme.jetbrains.notifications

import com.draculatheme.jetbrains.DraculaMeta
import com.intellij.ide.BrowserUtil
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import org.intellij.lang.annotations.Language

object DraculaNotification {

    @Language("HTML")
    private val whatsNew = """
        <ul>
            <li>Fix welcome window action button background color</li>
        </ul>
    """.trimIndent()

    @Language("HTML")
    private val releaseNote = """
        <p>What's New?</p>
        $whatsNew
    """.trimIndent()

    @Language("HTML")
    private val welcomeMessage = """
        <p>Thank you for choosing Dracula.</p>
    """.trimIndent()

    private const val notificationGroupId = "Dracula Theme"

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    private const val changelogLink = "https://github.com/dracula/jetbrains/blob/master/CHANGELOG.md"
    private const val draculaProLink = "https://gumroad.com/a/477820019"
    private const val githubRepoLink = "https://github.com/dracula/jetbrains"

    fun notifyReleaseNote(project: Project) {
        val title = "Dracula Theme updated to v${DraculaMeta.currentVersion}"
        val notification = NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId)
            .createNotification(title, releaseNote, NotificationType.INFORMATION)
        addNotificationActions(notification)
        notification.icon = notificationIcon
        notification.notify(project)
    }

    fun notifyFirstlyDownloaded(project: Project) {
        val title = "Dracula Theme is installed"
        val notification = NotificationGroupManager.getInstance().getNotificationGroup(notificationGroupId)
            .createNotification(title, welcomeMessage, NotificationType.INFORMATION)
        addNotificationActions(notification)
        notification.icon = notificationIcon
        notification.notify(project)
    }

    private fun addNotificationActions(notification: Notification) {
        val actionChangelog = NotificationAction.createSimple("Changelog") {
            BrowserUtil.browse(changelogLink)
        }
        val actionDraculaPro = NotificationAction.createSimple("Dracula Pro") {
            BrowserUtil.browse(draculaProLink)
        }
        val actionGithubRepo = NotificationAction.createSimple("GitHub") {
            BrowserUtil.browse(githubRepoLink)
        }
        notification.addAction(actionChangelog)
        notification.addAction(actionDraculaPro)
        notification.addAction(actionGithubRepo)
    }

}
