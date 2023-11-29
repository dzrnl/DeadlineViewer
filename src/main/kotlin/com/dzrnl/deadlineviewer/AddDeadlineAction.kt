package com.dzrnl.deadlineviewer

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class AddDeadlinesAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        DeadlineDataDialogWrapper().showAndGet()
    }
}