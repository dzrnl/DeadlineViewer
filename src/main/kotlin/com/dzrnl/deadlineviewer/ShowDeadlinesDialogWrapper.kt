package com.dzrnl.deadlineviewer

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import net.miginfocom.swing.MigLayout
import javax.swing.JComponent
import javax.swing.JPanel

class ShowDeadlinesDialogWrapper : DialogWrapper(true) {
    private val panel = JPanel(MigLayout())

    init {
        init()
        title = "Deadlines"
    }

    override fun createCenterPanel(): JComponent {
        val state = Settings.getInstance().state
        state?.deadlines?.forEach {
            panel.add(JBLabel(it.name + " | " + it.date + " | " + it.description), "wrap")
        }
        if (state?.deadlines?.isEmpty() == true) {
            panel.add(JBLabel("No deadlines"))
        }

        return panel
    }
}