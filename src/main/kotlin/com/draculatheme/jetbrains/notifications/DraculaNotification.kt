package com.draculatheme.jetbrains.notifications

import com.draculatheme.jetbrains.DraculaMeta
import com.intellij.ide.BrowserUtil
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.ColorUtil
import com.intellij.ui.JBColor
import org.intellij.lang.annotations.Language
import java.awt.Color

object DraculaNotification {

    // Balloons are painted with whatever LaF the IDE is currently running, not with our
    // theme. The install notification in particular fires before the user has picked
    // Dracula at all, and Alucard is itself a light theme, so a fixed dark-on-light
    // palette would render unreadable. JBColor picks the right side per render.
    private object Colors {
        val PRIMARY = JBColor(Color(0x644AC9), Color(0xBD93F9))     // Alucard / Dracula purple
        val ACCENT = JBColor(Color(0x036A96), Color(0x8BE9FD))      // Alucard blue / Dracula cyan
        val TEXT_PRIMARY = JBColor(Color(0x1F1F1F), Color(0xF8F8F2))
        val TEXT_SECONDARY = JBColor(Color(0x6C664B), Color(0x6272A4))
        val BACKGROUND = JBColor(Color(0xEDEBE0), Color(0x33374A))  // info box fill
        val BORDER = JBColor(Color(0xBCBAB3), Color(0x6272A4))
    }

    private fun css(color: JBColor) = "#${ColorUtil.toHex(color)}"

    // Declared as getters, not constants: JBColor resolves against the LaF that is active
    // when the balloon is built, so these have to be read at notify time rather than at
    // class-init time. Swing's HTML renderer is HTML 3.2 — no rgba(), no border-radius,
    // and font-weight only understands bold/normal — so everything here stays within that.
    private object Styles {
        val CONTAINER get() = "margin: 8px 0; line-height: 1.4;"
        val HEADING get() = "margin: 0 0 8px 0; color: ${css(Colors.PRIMARY)}; font-size: 14px; font-weight: bold;"
        val PARAGRAPH get() = "margin: 0 0 12px 0; color: ${css(Colors.TEXT_PRIMARY)}; font-size: 13px;"
        val SMALL_TEXT
            get() = "margin: 12px 0 0 0; color: ${css(Colors.TEXT_SECONDARY)}; font-size: 12px; font-style: italic;"
        val LIST_ITEM get() = "margin: 6px 0; color: ${css(Colors.TEXT_PRIMARY)};"
        val INFO_BOX
            get() = "background: ${css(Colors.BACKGROUND)}; border-left: 3px solid ${css(Colors.BORDER)}; padding: 10px 12px; margin: 12px 0;"
        val HIGHLIGHT get() = "color: ${css(Colors.ACCENT)}; font-weight: bold;"
        val INFO_TEXT get() = "margin: 0; color: ${css(Colors.TEXT_PRIMARY)}; font-size: 12px;"
    }

    @Language("HTML")
    private fun whatsNew() = """
        <div style="${Styles.CONTAINER}">
            <h4 style="${Styles.HEADING}">✨ What's New</h4>
            <ul style="margin: 0; padding-left: 18px;">
                <li style="${Styles.LIST_ITEM}">🎨 Alucard now colors Go, Python, Ruby, CSS, Markdown and more with its own palette</li>
                <li style="${Styles.LIST_ITEM}">🎨 Cleaner punctuation coloring in Dracula — braces, brackets and operators finally match the theme</li>
                <li style="${Styles.LIST_ITEM}">🎨 Readable toggle switches in Alucard and clearer plugin Update buttons</li>
                <li style="${Styles.LIST_ITEM}">🎨 Islands Alucard now matches its dark counterparts</li>
                <li style="${Styles.LIST_ITEM}">✨ Notifications show their intended styling on every theme</li>
            </ul>
        </div>
    """.trimIndent()

    @Language("HTML")
    private fun releaseNote() = """
        <div style="${Styles.CONTAINER}">
            <p style="${Styles.PARAGRAPH}">
                🎉 <strong>Welcome to Dracula Theme v${DraculaMeta.currentVersion}!</strong> Here's what's new in this release:
            </p>
            ${whatsNew()}
            <div style="${Styles.INFO_BOX}">
                <p style="${Styles.INFO_TEXT}">
                    💡 <strong>Pro tip:</strong> Check out <span style="${Styles.HIGHLIGHT}">Dracula PRO</span> for even more customization options!
                </p>
            </div>
            <p style="${Styles.SMALL_TEXT}">
                Enjoy the latest improvements! 🧛‍♂️
            </p>
        </div>
    """.trimIndent()

    @Language("HTML")
    private fun welcomeMessage() = """
        <div style="${Styles.CONTAINER}">
            <p style="${Styles.PARAGRAPH}">
                🎉 <strong>Welcome to the dark side!</strong> Dracula Theme is now installed and ready to transform your coding experience.
            </p>
            <div style="${Styles.INFO_BOX}">
                <p style="${Styles.INFO_TEXT}">
                    💡 <strong>Quick Setup:</strong>
                </p>
                <ul style="margin: 8px 0 0 0; padding-left: 16px; color: ${css(Colors.TEXT_PRIMARY)}; font-size: 12px;">
                    <li style="margin: 4px 0;">Go to <span style="${Styles.HIGHLIGHT}">Settings → Appearance & Behavior → Appearance</span></li>
                    <li style="margin: 4px 0;">Select <span style="${Styles.HIGHLIGHT}">Dracula</span> from the Theme dropdown</li>
                    <li style="margin: 4px 0;">Restart your IDE for the best experience</li>
                </ul>
            </div>
            <p style="${Styles.SMALL_TEXT}">
                Happy coding with Dracula! 🧛‍♂️
            </p>
        </div>
    """.trimIndent()

    private const val NOTIFICATION_GROUP_ID = "Dracula Theme"

    private val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    private const val DRACULA_PRO_LINK = "https://gumroad.com/a/477820019"
    private const val GITHUB_LINK = "https://github.com/dracula/jetbrains"
    private const val DONATE_LINK = "https://www.buymeacoffee.com/nszihan"

    fun notifyReleaseNote(project: Project) {
        val title = "🎨 Dracula Theme v${DraculaMeta.currentVersion} - Release Notes"
        notify(project, title, releaseNote())
    }

    fun notifyFirstlyDownloaded(project: Project) {
        val title = "🧛‍♂️ Dracula Theme Successfully Installed"
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
