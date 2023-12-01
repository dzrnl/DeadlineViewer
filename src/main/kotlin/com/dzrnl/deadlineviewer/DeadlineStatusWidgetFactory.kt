package com.dzrnl.deadlineviewer

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.openapi.wm.impl.status.TextPanel
import com.intellij.ui.ClickListener
import org.jetbrains.annotations.Nls
import java.awt.event.MouseEvent
import javax.swing.JComponent

class DeadlineStatusWidgetFactory : StatusBarWidgetFactory {
    companion object {
        private const val ID = "DeadlineViewer"
    }

    override fun getId(): String = ID

    @Nls
    override fun getDisplayName(): String = "Deadline Viewer"

    override fun isAvailable(project: Project): Boolean = true

    override fun createWidget(project: Project): StatusBarWidget = DeadlineViewerStatusWidget()

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true

    override fun disposeWidget(widget: StatusBarWidget) {}

    private class DeadlineViewerStatusWidget : TextPanel(), CustomStatusBarWidget {
        init {
            object : ClickListener() {
                override fun onClick(event: MouseEvent, clickCount: Int): Boolean {
                    SetDeadlineDialogWrapper().show()
                    val state = Settings.getInstance().state
                    text = state?.deadlineDate
                    return true
                }
            }.installOn(this, true)
        }

        override fun ID(): String = ID

        override fun install(statusBar: StatusBar) {
            val state = Settings.getInstance().state
            text = if (state?.deadlineExists == false) {
                "no deadline"
            } else {
                state?.deadlineDate
            }
        }

        override fun getComponent(): JComponent = this

        override fun dispose() {}
    }
}