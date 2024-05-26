package com.github.Ringoame196

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class Yml(private val file: File) {
    private fun acquisitionYml(): YamlConfiguration {
        if (!file.exists()) {
            file.mkdirs()
        }
        return YamlConfiguration.loadConfiguration(file)
    }
    fun acquisitionValue(key: String): Int {
        val ymlFile = acquisitionYml()
        return ymlFile.getInt(key)
    }
    fun setValue(key: String, value: Int?) {
        val ymlFile = acquisitionYml()
        ymlFile.set(key, value)
        ymlFile.save(file)
    }
}
