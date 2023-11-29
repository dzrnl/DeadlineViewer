package com.dzrnl.deadlineviewer

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class AddDeadlineAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        AddDeadlineDialogWrapper().showAndGet()
    }
}