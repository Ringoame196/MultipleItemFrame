package com.github.Ringoame196.Manager

import com.github.Ringoame196.FileManagement.Yml
import com.github.Ringoame196.Display.CountDisplay
import org.bukkit.Sound
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.ItemFrame
import org.bukkit.entity.Player
import java.io.File

class MultipleItemFrame(private val itemFrame: ItemFrame, dataFile: File, messageFile: File) {
    private val dataFile = Yml(dataFile)
    private val messageFile = Yml(messageFile)
    private val itemFrameUUID = itemFrame.uniqueId.toString()
    private val countKey = "$itemFrameUUID.count"
    private val countDisplayKey = "$itemFrameUUID.countDisplayKey"
    private val countDisplay = CountDisplay(this.dataFile, itemFrame, countDisplayKey)

    fun checkBlockItemFrame(config: FileConfiguration): Boolean {
        val blockTag = config.getString("blockTag") ?: return true
        if (blockTag == "") return true
        return !itemFrame.scoreboardTags.contains(blockTag)
    }

    fun additionItem(player: Player, config: FileConfiguration): Boolean {
        val maxCount = config.getInt("maxCount")
        val count = acquisitionCount() + 1
        if (count > maxCount) {
            sendOverMessage(player)
            return false
        }
        dataFile.setValue(countKey, count)
        countDisplay.display(count)
        return true
    }
    fun fetchItems() {
        val count = acquisitionCount()
        if (count == 0) return // そもそもデータがなければ実行しない
        dropItemFrameItem(count)
        countDisplay.delete()
        dataFile.setValue(itemFrameUUID, null)
    }
    private fun dropItemFrameItem(amount: Int) {
        val location = itemFrame.location
        val world = location.world
        val item = itemFrame.item.clone()
        item.amount = amount
        world?.dropItem(location, item)
    }
    private fun acquisitionCount(): Int {
        return dataFile.acquisitionIntValue(countKey)
    }
    private fun sendOverMessage(player: Player) {
        val message = messageFile.acquisitionStringValue("OverMessage")
        player.sendMessage(message)
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f)
    }
}
