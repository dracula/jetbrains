package com.draculatheme.jetbrains.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service

@State(name = "DraculaSetting", storages = [Storage("dracula-theme.xml")])
class DraculaSettings : PersistentStateComponent<DraculaState> {
    companion object {
        val instance: DraculaSettings
            get() = service<DraculaSettings>()
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