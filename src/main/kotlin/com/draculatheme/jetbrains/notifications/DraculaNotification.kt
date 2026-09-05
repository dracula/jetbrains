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

    // Inherit the notification renderer's font and foreground so content follows the
    // active IDE theme and UI font settings, including before Dracula is selected.
    @Language("HTML")
    private fun releaseNote() = """
        <ul style="margin: 0; padding-left: 18px;">
            <li style="margin-bottom: 6px;"><b>Terminal:</b> Theme colors in the Reworked terminal for all six variants.</li>
            <li style="margin-bottom: 6px;"><b>Panels:</b> Consistent backgrounds in Dracula and Dracula Colorful.</li>
            <li><b>Controls:</b> Clearer selections and hover states, with subtle borders and progress tracks.</li>
        </ul>
    """.trimIndent()

    @Language("HTML")
    private fun welcomeMessage() = """
        <p style="margin: 0 0 8px 0;">Choose your theme in <b>Settings → Appearance &amp; Behavior → Appearance</b>.</p>
        <p style="margin: 0;">Select <b>Dracula</b>, <b>Dracula Colorful</b> or <b>Dracula Alucard</b>, or try an <b>Islands</b> variant.</p>
    """.trimIndent()

    private const val NOTIFICATION_GROUP_ID = "Dracula Theme"

    private val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    private const val DRACULA_PRO_LINK = "https://gumroad.com/a/477820019"
    private const val DONATE_LINK = "https://www.buymeacoffee.com/nszihan"

    fun notifyReleaseNote(project: Project) {
        val title = "Dracula Theme ${DraculaMeta.currentVersion} — What’s new"
        notify(project, title, releaseNote())
    }

    fun notifyFirstlyDownloaded(project: Project) {
        val title = "Dracula Theme installed"
        notify(project, title, welcomeMessage())
    }

    private fun notify(project: Project, title: String, content: String) {
        val notification = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)
            .createNotification(title, content, NotificationType.INFORMATION)
        addNotificationActions(notification)
        notification.icon = notificationIcon
        notification.notify(project)
    }

    private fun addNotificationActions(notification: Notification) {
        notification.addAction(NotificationAction.createSimple("🚀 Dracula PRO") {
            BrowserUtil.browse(DRACULA_PRO_LINK)
        })
        notification.addAction(NotificationAction.createSimple("☕ Buy me a coffee") {
            BrowserUtil.browse(DONATE_LINK)
        })
    }
}
