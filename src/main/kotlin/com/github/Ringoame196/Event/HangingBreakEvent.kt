package com.github.Ringoame196.Event

import com.github.Ringoame196.MultipleItemFrame
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
        val multipleItemFrame = MultipleItemFrame(itemFrame, dataFile, messageFile)
        if (!multipleItemFrame.checkBlockItemFrame(config)) return
        multipleItemFrame.fetchItems()
    }
}
