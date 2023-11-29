package com.dzrnl.deadlineviewer

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.Storage
@State(name = "DeadlineViewer", storages = [Storage("deadline.xml")])
class Settings : PersistentStateComponent<DeadlineViewerState> {
    private var pluginState = DeadlineViewerState()
    override fun getState() = pluginState

    override fun loadState(state: DeadlineViewerState) {
        pluginState = state
    }

    companion object {
        @JvmStatic
        fun getInstance(): PersistentStateComponent<DeadlineViewerState> {
            return ServiceManager.getService(Settings::class.java)
        }
    }
}