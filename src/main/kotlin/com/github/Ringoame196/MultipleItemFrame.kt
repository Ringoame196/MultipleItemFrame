package com.github.Ringoame196

import org.bukkit.entity.ItemFrame
import org.bukkit.plugin.Plugin
import java.io.File

class MultipleItemFrame(private val itemFrame: ItemFrame, private val plugin: Plugin) {
    private val file = File(plugin.dataFolder, "data.yml")
    private val yml = Yml(file)
    private val itemFrameUUID = itemFrame.uniqueId.toString()
    private val countKey = "$itemFrameUUID.count"
    private val countDisplayKey = "$itemFrameUUID.countDisplayKey"
    private val countDisplay = CountDisplay(yml, itemFrame, countDisplayKey)
    fun additionItem() {
        val count = acquisitionCount()
        yml.setValue(countKey, count + 1)
        countDisplay.display(count + 1)
    }
    fun fetchItems() {
        val count = acquisitionCount()
        if (count == 0) return
        dropItemFrameItem(count)
        countDisplay.delete()
        yml.setValue(itemFrameUUID, null)
    }
    private fun dropItemFrameItem(amount: Int) {
        val location = itemFrame.location
        val item = itemFrame.item.clone()
        item.amount = amount
        location.world?.dropItem(location, item)
    }
    private fun acquisitionCount(): Int {
        return yml.acquisitionIntValue(countKey)
    }
}
