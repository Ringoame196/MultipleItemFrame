package com.github.Ringoame196

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.hanging.HangingBreakEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.plugin.Plugin
import java.io.File

class Events(plugin: Plugin, dataFileName: String) : Listener {
    private val dataFile = File(plugin.dataFolder, dataFileName)
    @EventHandler
    fun onPlayerInteractEntity(e: PlayerInteractEntityEvent) {
        val player = e.player
        val playerItem = player.inventory.itemInMainHand.clone()
        val itemFrame = e.rightClicked
        if (!player.isSneaking) return
        if (itemFrame !is ItemFrame) return

        val multipleItemFrame = MultipleItemFrame(itemFrame, dataFile)
        val user = User(player)

        val itemFrameItem = itemFrame.item.clone()
        if (playerItem.type == Material.AIR || itemFrameItem.type == Material.AIR) return

        playerItem.amount = 1
        itemFrameItem.amount = 1

        if (playerItem != itemFrameItem) return
        e.isCancelled = true
        multipleItemFrame.additionItem()
        user.reduceMainHandItem()
        player.playSound(player, Sound.BLOCK_DISPENSER_FAIL, 1f, 1f)
    }
    @EventHandler
    fun onEntityDamage(e: EntityDamageEvent) {
        val itemFrame = e.entity
        if (itemFrame !is ItemFrame) return
        val multipleItemFrame = MultipleItemFrame(itemFrame, dataFile)
        multipleItemFrame.fetchItems()
    }
    @EventHandler
    fun onHangingBreak(e: HangingBreakEvent) {
        val itemFrame = e.entity
        if (itemFrame !is ItemFrame) return
        val multipleItemFrame = MultipleItemFrame(itemFrame, dataFile)
        multipleItemFrame.fetchItems()
    }
}
