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
            <li>Force switch to correspond editor color when toggle UI to Dracula</li>
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

    private const val NOTIFICATION_GROUP_ID = "Dracula Theme"

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    private const val DRACULA_PRO_LINK = "https://gumroad.com/a/477820019"
    private const val GITHUB_LING = "https://github.com/dracula/jetbrains"
    private const val DONATE_LINK = "https://www.buymeacoffee.com/nszihan"

    fun notifyReleaseNote(project: Project) {
        val title = "Dracula Theme updated to v${DraculaMeta.currentVersion}"
        val notification = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)
            .createNotification(title, releaseNote, NotificationType.INFORMATION)
        addNotificationActions(notification)
        notification.icon = notificationIcon
        notification.notify(project)
    }

    fun notifyFirstlyDownloaded(project: Project) {
        val title = "Dracula Theme is installed"
        val notification = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)
            .createNotification(title, welcomeMessage, NotificationType.INFORMATION)
        addNotificationActions(notification)
        notification.icon = notificationIcon
        notification.notify(project)
    }

    private fun addNotificationActions(notification: Notification) {
        val actionDraculaPro = NotificationAction.createSimple("Dracula PRO") {
            BrowserUtil.browse(DRACULA_PRO_LINK)
        }
        val github = NotificationAction.createSimple("GitHub") {
            BrowserUtil.browse(GITHUB_LING)
        }
        val actionDonate = NotificationAction.createSimple("Donate") {
            BrowserUtil.browse(DONATE_LINK)
        }
        notification.addAction(actionDraculaPro)
        notification.addAction(github)
        notification.addAction(actionDonate)
    }

}
