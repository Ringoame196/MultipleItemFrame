package com.github.Ringoame196

import org.bukkit.Bukkit
import org.bukkit.entity.ItemFrame
import org.bukkit.plugin.Plugin
import java.io.File

class MultipleItemFrame(private val itemFrame: ItemFrame, private val plugin: Plugin) {
    private val file = File(plugin.dataFolder, "countData.yml")
    private val yml = Yml(file)
    private val itemFrameUUID = itemFrame.uniqueId.toString()
    fun additionItem() {
        var count = acquisitionCount()
        count ++
        yml.setValue(itemFrameUUID, count)
        Bukkit.broadcastMessage((count + 1).toString())
    }
    fun fetchItems() {
        val count = acquisitionCount()
        val item = itemFrame.item
        val location = itemFrame.location
        item.amount = count
        location.world?.dropItem(location, item)
        yml.setValue(itemFrameUUID, null)
    }
    private fun acquisitionCount(): Int {
        return yml.acquisitionValue(itemFrameUUID)
    }
}
