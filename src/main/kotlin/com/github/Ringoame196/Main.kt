package com.github.Ringoame196

import com.github.Ringoame196.Event.EntityDamageEvent
import com.github.Ringoame196.Event.HangingBreakEvent
import com.github.Ringoame196.Event.PlayerInteractEntity
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        val dataFolderPath = this.dataFolder
        val dataFileName = "data.yml"
        val messageFileName = "message.yml"
        val dataFile = File(dataFolderPath, dataFileName)
        val messageFile = File(dataFolderPath, messageFileName)
        val config = this.config

        server.pluginManager.registerEvents(PlayerInteractEntity(dataFile, messageFile, config), this)
        server.pluginManager.registerEvents(EntityDamageEvent(dataFile, messageFile, config), this)
        server.pluginManager.registerEvents(HangingBreakEvent(dataFile, messageFile, config), this)
        if (!File(dataFileName).exists()) saveResource(dataFileName, false)
        if (!File(messageFileName).exists())saveResource(messageFileName, false)

        saveDefaultConfig()
    }
}
