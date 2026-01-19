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

    // Dracula Theme Color Palette
    private object Colors {
        const val PRIMARY = "#BD93F9"      // Purple
        const val SECONDARY = "#6272A4"    // Muted Blue
        const val ACCENT = "#8BE9FD"       // Cyan
        const val TEXT_PRIMARY = "#F8F8F2" // Light Text
        const val TEXT_SECONDARY = "#6272A4" // Muted Text
        const val BACKGROUND = "rgba(98, 114, 164, 0.08)" // Subtle background
        const val BORDER = "#6272A4"       // Border color
    }

    // Common CSS styles for consistency
    private object Styles {
        const val CONTAINER = "margin: 8px 0; line-height: 1.4;"
        const val HEADING = "margin: 0 0 8px 0; color: ${Colors.PRIMARY}; font-size: 14px; font-weight: 600;"
        const val PARAGRAPH = "margin: 0 0 12px 0; color: ${Colors.TEXT_PRIMARY}; font-size: 13px;"
        const val SMALL_TEXT = "margin: 12px 0 0 0; color: ${Colors.TEXT_SECONDARY}; font-size: 12px; font-style: italic;"
        const val LIST_ITEM = "margin: 6px 0; color: ${Colors.TEXT_PRIMARY};"
        const val INFO_BOX = "background: ${Colors.BACKGROUND}; border-left: 3px solid ${Colors.BORDER}; padding: 10px 12px; margin: 12px 0; border-radius: 3px;"
        const val HIGHLIGHT = "color: ${Colors.ACCENT}; font-weight: 500;"
    }

    @Language("HTML")
    private val whatsNew = """
        <div style="$Styles.CONTAINER">
            <h4 style="$Styles.HEADING">✨ What's New</h4>
            <ul style="margin: 0; padding-left: 18px;">
                <li style="$Styles.LIST_ITEM">🔔 Improve notification card background contrast in Islands themes for better visibility</li>
            </ul>
        </div>
    """.trimIndent()

    @Language("HTML")
    private val releaseNote = """
        <div style="$Styles.CONTAINER">
            <p style="$Styles.PARAGRAPH">
                🎉 <strong>Welcome to Dracula Theme v${DraculaMeta.currentVersion}!</strong> Here's what's new in this release:
            </p>
            $whatsNew
            <div style="$Styles.INFO_BOX">
                <p style="margin: 0; color: ${Colors.TEXT_PRIMARY}; font-size: 12px;">
                    💡 <strong>Pro tip:</strong> Check out <span style="$Styles.HIGHLIGHT">Dracula PRO</span> for even more customization options!
                </p>
            </div>
            <p style="$Styles.SMALL_TEXT">
                Enjoy the latest improvements! 🧛‍♂️
            </p>
        </div>
    """.trimIndent()

    @Language("HTML")
    private val welcomeMessage = """
        <div style="$Styles.CONTAINER">
            <p style="$Styles.PARAGRAPH">
                🎉 <strong>Welcome to the dark side!</strong> Dracula Theme is now installed and ready to transform your coding experience.
            </p>
            <div style="$Styles.INFO_BOX">
                <p style="margin: 0 0 8px 0; color: ${Colors.TEXT_PRIMARY}; font-size: 12px;">
                    💡 <strong>Quick Setup:</strong>
                </p>
                <ul style="margin: 0; padding-left: 16px; color: ${Colors.TEXT_PRIMARY}; font-size: 12px;">
                    <li style="margin: 4px 0;">Go to <span style="$Styles.HIGHLIGHT">Settings → Appearance & Behavior → Appearance</span></li>
                    <li style="margin: 4px 0;">Select <span style="$Styles.HIGHLIGHT">Dracula</span> from the Theme dropdown</li>
                    <li style="margin: 4px 0;">Restart your IDE for the best experience</li>
                </ul>
            </div>
            <p style="$Styles.SMALL_TEXT">
                Happy coding with Dracula! 🧛‍♂️
            </p>
        </div>
    """.trimIndent()

    private const val NOTIFICATION_GROUP_ID = "Dracula Theme"

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    private const val DRACULA_PRO_LINK = "https://gumroad.com/a/477820019"
    private const val GITHUB_LINK = "https://github.com/dracula/jetbrains"
    private const val DONATE_LINK = "https://www.buymeacoffee.com/nszihan"

    fun notifyReleaseNote(project: Project) {
        val title = "🎨 Dracula Theme v${DraculaMeta.currentVersion} - Release Notes"
        val notification = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)
            .createNotification(title, releaseNote, NotificationType.INFORMATION)
        addNotificationActions(notification)
        notification.icon = notificationIcon
        notification.notify(project)
    }

    fun notifyFirstlyDownloaded(project: Project) {
        val title = "🧛‍♂️ Dracula Theme Successfully Installed"
        val notification = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)
            .createNotification(title, welcomeMessage, NotificationType.INFORMATION)
        addNotificationActions(notification)
        notification.icon = notificationIcon
        notification.notify(project)
    }

    private fun addNotificationActions(notification: Notification) {
        val actionDraculaPro = NotificationAction.createSimple("🚀 Dracula PRO") {
            BrowserUtil.browse(DRACULA_PRO_LINK)
        }
        val github = NotificationAction.createSimple("📖 GitHub") {
            BrowserUtil.browse(GITHUB_LINK)
        }
        val actionDonate = NotificationAction.createSimple("☕ Donate") {
            BrowserUtil.browse(DONATE_LINK)
        }
        notification.addAction(actionDraculaPro)
        notification.addAction(github)
        notification.addAction(actionDonate)
    }
}
