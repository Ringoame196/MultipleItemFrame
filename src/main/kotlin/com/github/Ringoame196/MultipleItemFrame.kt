package com.github.Ringoame196

import org.bukkit.entity.ItemFrame
import java.io.File

class MultipleItemFrame(private val itemFrame: ItemFrame, dataFile: File) {
    private val yml = Yml(dataFile)
    private val itemFrameUUID = itemFrame.uniqueId.toString()
    private val countKey = "$itemFrameUUID.count"
    private val countDisplayKey = "$itemFrameUUID.countDisplayKey"
    private val countDisplay = CountDisplay(yml, itemFrame, countDisplayKey)
    fun additionItem() {
        val count = acquisitionCount() + 1
        yml.setValue(countKey, count)
        countDisplay.display(count)
    }
    fun fetchItems() {
        val count = acquisitionCount()
        if (count == 0) return // そもそもデータがなければ実行しない
        dropItemFrameItem(count)
        countDisplay.delete()
        yml.setValue(itemFrameUUID, null)
    }
    private fun dropItemFrameItem(amount: Int) {
        val location = itemFrame.location
        val world = location.world
        val item = itemFrame.item.clone()
        item.amount = amount
        world?.dropItem(location, item)
    }
    private fun acquisitionCount(): Int {
        return yml.acquisitionIntValue(countKey)
    }
}
