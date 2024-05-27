package com.github.Ringoame196

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val dataFileName = "data.yml"
        val messageName = "message.yml"
        val event = Events(this, dataFileName, messageName)
        server.pluginManager.registerEvents(event, this)
        if (!File(dataFileName).exists()) saveResource(dataFileName, false)
        if (!File(messageName).exists())saveResource(messageName, false)
        saveDefaultConfig()
    }
}
