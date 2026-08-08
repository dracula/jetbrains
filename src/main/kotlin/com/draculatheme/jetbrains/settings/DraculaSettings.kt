package com.draculatheme.jetbrains.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "DraculaSetting", storages = [Storage("dracula-theme.xml")])
class DraculaSettings : PersistentStateComponent<DraculaState> {
    companion object {
        val instance: DraculaSettings
            get() = ApplicationManager.getApplication().getService(DraculaSettings::class.java)
    }

    private var myState = DraculaState()

    val version: String
        @Synchronized get() = myState.version

    /**
     * Records [newVersion] and returns the version it replaced, or `null` if it was already
     * the stored version.
     *
     * This is a single atomic step because the caller is a per-project startup activity: opening
     * several projects at once would otherwise let every one of them read the old version before
     * any of them wrote the new one, and each would post its own balloon.
     */
    @Synchronized
    fun exchangeVersion(newVersion: String): String? {
        val previous = myState.version
        if (previous == newVersion) return null
        myState.version = newVersion
        return previous
    }

    @Synchronized
    override fun getState(): DraculaState {
        return myState
    }

    @Synchronized
    override fun loadState(state: DraculaState) {
        myState = state
    }

}
