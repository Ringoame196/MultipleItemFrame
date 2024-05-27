package com.github.Ringoame196

import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.ItemFrame
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.File

class MultipleItemFrame(private val plugin: Plugin, private val itemFrame: ItemFrame, dataFile: File) {
    private val yml = Yml(dataFile)
    private val itemFrameUUID = itemFrame.uniqueId.toString()
    private val countKey = "$itemFrameUUID.count"
    private val countDisplayKey = "$itemFrameUUID.countDisplayKey"
    private val countDisplay = CountDisplay(yml, itemFrame, countDisplayKey)
    fun additionItem(player: Player): Boolean {
        val maxCount = plugin.config.getInt("maxCount")
        val count = acquisitionCount() + 1
        if (count > maxCount) {
            sendOverMessage(player)
            return false
        }
        yml.setValue(countKey, count)
        countDisplay.display(count)
        return true
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
    private fun sendOverMessage(player: Player) {
        val message = "${ChatColor.RED}アイテムの最大数をオーバーしました"
        player.sendMessage(message)
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f)
    }
}
