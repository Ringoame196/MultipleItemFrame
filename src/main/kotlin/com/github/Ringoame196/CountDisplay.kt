package com.github.Ringoame196

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.ItemFrame
import java.util.UUID

class CountDisplay(private val yml: Yml, private val itemFrame: ItemFrame, private val key: String) {
    fun display(count: Int) {
        if (acquisitionUUID() == null) make(count)
        else update(count)
    }
    fun delete() {
        val uuid = acquisitionUUID() ?: return
        val countDisplay = acquisitionCountDisplay(uuid) ?: return
        countDisplay.remove()
    }
    private fun make(count: Int) {
        val location = itemFrame.location
        val armorStand = summonArmorStand(location, count) ?: return
        yml.setValue(key, armorStand.uniqueId.toString())
    }
    private fun summonArmorStand(location: Location, count: Int): ArmorStand? {
        val world = location.world
        // アーマースタンドを召喚
        location.add(0.0, -1.0, 0.0)
        val armorStand: ArmorStand? = world?.spawn(location, ArmorStand::class.java)
        armorStand?.let {
            // アーマースタンドの設定
            it.isVisible = false // 可視化するかどうか
            it.isSmall = true // サイズを小さくするかどうか
            it.isInvulnerable = true // 無敵
            it.customName = "${count}個"
            it.isCustomNameVisible = true
            it.isMarker = true // あたり判定を消す
        }
        return armorStand
    }
    private fun update(count: Int) {
        val uuid = acquisitionUUID() ?: return
        val countDisplay = acquisitionCountDisplay(uuid)
        if (countDisplay == null) make(count)
        else countDisplay.customName = "${count}個"
    }
    private fun acquisitionUUID(): UUID? {
        val uuidString = yml.acquisitionStringValue(key)
        return try { UUID.fromString(uuidString) } catch (e: Exception) { null }
    }
    private fun acquisitionCountDisplay(uuid: UUID): Entity? {
        val entity = Bukkit.getEntity(uuid)
        return entity
    }
}
