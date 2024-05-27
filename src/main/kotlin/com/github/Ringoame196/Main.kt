package com.github.Ringoame196

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val dataFileName = "data.yml"
        val messageName = "message.yml"
        val event = Events(this, dataFileName, messageName)
        server.pluginManager.registerEvents(event, this)
        saveResource(dataFileName, false)
        saveResource(messageName, false)
        saveDefaultConfig()
    }
}
