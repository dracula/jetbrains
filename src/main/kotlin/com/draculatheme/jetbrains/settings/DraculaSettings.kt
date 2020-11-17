package com.draculatheme.jetbrains.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "DraculaSetting", storages = [Storage("dracula-theme.xml")])
class DraculaSettings : PersistentStateComponent<DraculaState> {
    companion object {
        val instance: DraculaSettings
            get() = ServiceManager.getService(DraculaSettings::class.java)
    }

    private var myState = DraculaState()

    var version: String
        get() = myState.version
        set(value) {
            myState.version = value
        }

    override fun getState(): DraculaState? {
        return myState
    }

    override fun loadState(state: DraculaState) {
        myState = state
    }

}