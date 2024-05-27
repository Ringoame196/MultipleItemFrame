package com.github.Ringoame196

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val dataFileName = "data.yml"
        val event = Events(this, dataFileName)
        server.pluginManager.registerEvents(event, this)
        saveResource(dataFileName, false)
    }
}
