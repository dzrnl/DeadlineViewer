package com.dzrnl.deadlineviewer

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ShowDeadlinesAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        ShowDeadlinesDialogWrapper().show()
    }
}