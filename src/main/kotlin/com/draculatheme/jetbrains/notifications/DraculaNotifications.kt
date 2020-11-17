package com.draculatheme.jetbrains.notifications

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader

object DraculaNotifications {
    private val releaseNote = """
        What's New?<br>
            - Enhanced color schemes<br>
            - Enhanced file status colors<br>
        Please visit the <a href="https://github.com/dracula/jetbrains/blob/master/CHANGELOG.md">Changelog</a> for more details.<br>
        For premium package, please explore <a href="https://gumroad.com/a/477820019">Dracula PRO</a>.<br>
        Thank you for choosing Dracula.
    """.trimIndent()

    @JvmField
    val notificationIcon = IconLoader.getIcon("/icons/dracula-logo.svg", javaClass)

    private val notificationGroup = NotificationGroup(
            displayId = "Dracula Theme",
            displayType = NotificationDisplayType.STICKY_BALLOON,
            isLogByDefault = true
    )

    fun notifyReleaseNote(project: Project) {
        notificationGroup.createNotification(
                title = "Dracula Theme",
                content = releaseNote,
                type = NotificationType.INFORMATION,
                listener = NotificationListener.URL_OPENING_LISTENER
        )
                .setIcon(notificationIcon)
                .notify(project)
    }

}