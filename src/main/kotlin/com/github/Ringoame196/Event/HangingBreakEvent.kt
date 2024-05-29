package com.github.Ringoame196.Event

import com.github.Ringoame196.Manager.MultipleItemFrameManager
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.hanging.HangingBreakEvent
import java.io.File

class HangingBreakEvent(private val dataFile: File, private val messageFile: File, private val config: FileConfiguration) : org.bukkit.event.Listener {
    @EventHandler
    fun onHangingBreak(e: HangingBreakEvent) {
        val itemFrame = e.entity
        if (itemFrame !is ItemFrame) return
        val multipleItemFrameManager = MultipleItemFrameManager(itemFrame, dataFile, messageFile)
        if (!multipleItemFrameManager.checkBlockItemFrame(config)) return
        multipleItemFrameManager.fetchItems()
    }
}
