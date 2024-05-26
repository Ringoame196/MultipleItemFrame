package com.github.Ringoame196

import org.bukkit.entity.Player

class User(private val player: Player) {
    fun reduceMainHandItem() {
        val item = player.inventory.itemInMainHand.clone()
        item.amount --
        player.inventory.setItemInMainHand(item)
    }
}
