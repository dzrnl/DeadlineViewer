package com.dzrnl.deadlineviewer

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.michaelbaranov.microba.calendar.DatePicker
import net.miginfocom.swing.MigLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

class DeadlineDataDialogWrapper : DialogWrapper(true) {
    private val panel = JPanel(MigLayout())
    private val nameField = JTextField("My Deadline") //
    private val datePicker = DatePicker()
    private val descriptionArea = JBTextArea()

    init {
        init()
        title = "Add Deadline"

        descriptionArea.lineWrap = true
        descriptionArea.wrapStyleWord = true
    }

    override fun createCenterPanel(): JComponent {
        panel.add(JBLabel("Deadline name:"))
        panel.add(nameField, "wrap, pushx, growx")
        panel.add(JBLabel("Date:"))
        panel.add(datePicker, "wrap, pushx, growx")
        panel.add(JBLabel("Description:"))
        panel.add(descriptionArea, "wrap, pushx, growx")

        return panel
    }

    override fun doOKAction() {
        super.doOKAction()

        val state = Settings.getInstance().state
        state?.deadlines?.add(Deadline(nameField.text, descriptionArea.text))
    }
}