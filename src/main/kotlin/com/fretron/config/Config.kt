package com.fretron.config

import java.io.FileInputStream
import java.io.IOException
import java.util.*

class Config {
    private val properties = Properties()

    @Throws(IOException::class)
    fun load(filePath: String) {
        FileInputStream(filePath).use { inputStream ->
            if (filePath.endsWith(".xml")) {
                properties.loadFromXML(inputStream)
            } else {
                properties.load(inputStream)
            }
        }
    }

    fun getString(key: String): String? {
        return properties.getProperty(key)
    }

    fun getBoolean(key: String): Boolean {
        return properties.getProperty(key)?.toBoolean() ?: false
    }
}
