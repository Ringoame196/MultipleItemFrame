package com.github.Ringoame196.Event

import com.github.Ringoame196.Manager.MultipleItemFrameManager
import com.github.Ringoame196.Manager.UserManager
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import java.io.File

class PlayerInteractEntity(private val dataFile: File, private val messageFile: File, private val config: FileConfiguration) : Listener {
    @EventHandler
    fun onPlayerInteractEntity(e: PlayerInteractEntityEvent) {
        val player = e.player
        val playerItem = player.inventory.itemInMainHand.clone()
        val itemFrame = e.rightClicked
        if (!player.isSneaking) return
        if (itemFrame !is ItemFrame) return

        val multipleItemFrameManager = MultipleItemFrameManager(itemFrame, dataFile, messageFile)
        val userManager = UserManager(player)

        if (!multipleItemFrameManager.checkBlockItemFrame(config)) return

        val itemFrameItem = itemFrame.item.clone()
        if (playerItem.type == Material.AIR || itemFrameItem.type == Material.AIR) return

        playerItem.amount = 1
        itemFrameItem.amount = 1

        if (playerItem != itemFrameItem) return
        if (!multipleItemFrameManager.additionItem(player, config)) return
        e.isCancelled = true
        userManager.reduceMainHandItem()
        player.playSound(player, Sound.BLOCK_DISPENSER_FAIL, 1f, 1f)
    }
}
