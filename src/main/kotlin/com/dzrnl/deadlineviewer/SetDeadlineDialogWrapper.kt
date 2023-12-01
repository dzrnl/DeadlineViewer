package com.dzrnl.deadlineviewer

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.components.JBTextField
import com.michaelbaranov.microba.calendar.DatePicker
import net.miginfocom.swing.MigLayout
import java.text.SimpleDateFormat
import javax.swing.JComponent
import javax.swing.JPanel
import java.util.Date
import javax.swing.JButton

class SetDeadlineDialogWrapper : DialogWrapper(true) {
    private val panel = JPanel(MigLayout())
    private val nameField = JBTextField("My Deadline")
    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
    private val datePicker = DatePicker(Date(), dateFormatter)
    private val descriptionArea = JBTextArea()
    private val removeButton = JButton("Remove deadline")

    init {
        init()
        title = "Set Deadline"

        descriptionArea.lineWrap = true
        descriptionArea.wrapStyleWord = true
        removeButton.addActionListener {
            val state = Settings.getInstance().state
            state?.deadlineName = ""
            state?.deadlineDate = "no deadline"
            state?.deadlineDescription = ""
            state?.deadlineExists = false

            doCancelAction()
        }
    }

    override fun createCenterPanel(): JComponent {
        panel.add(JBLabel("Deadline name:"))
        panel.add(nameField, "wrap, pushx, growx")
        panel.add(JBLabel("Date:"))
        panel.add(datePicker, "wrap, pushx, growx")
        panel.add(JBLabel("Description:"))
        panel.add(descriptionArea, "wrap, pushx, growx")
        panel.add(removeButton, "wrap, pushx, growx")

        val state = Settings.getInstance().state
        if (state?.deadlineExists == true) {
            nameField.text = state.deadlineName
            datePicker.date = dateFormatter.parse(state.deadlineDate)
            descriptionArea.text = state.deadlineDescription
        }

        return panel
    }

    override fun doOKAction() {
        super.doOKAction()

        val state = Settings.getInstance().state
        state?.deadlineName = nameField.text
        state?.deadlineDate = dateFormatter.format(datePicker.date)
        state?.deadlineDescription = descriptionArea.text
        state?.deadlineExists = true
    }
}