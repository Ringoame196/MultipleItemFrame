package com.github.Ringoame196

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class Yml(private val file: File) {
    private fun acquisitionYml(): YamlConfiguration {
        return YamlConfiguration.loadConfiguration(file)
    }
    fun acquisitionIntValue(key: String): Int {
        val ymlFile = acquisitionYml()
        return ymlFile.getInt(key)
    }
    fun acquisitionStringValue(key: String): String? {
        val ymlFile = acquisitionYml()
        return ymlFile.getString(key)
    }
    fun setValue(key: String, value: Any?) {
        val ymlFile = acquisitionYml()
        ymlFile.set(key, value)
        ymlFile.save(file)
    }
}
