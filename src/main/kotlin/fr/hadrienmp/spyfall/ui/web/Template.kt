package fr.hadrienmp.spyfall.ui.web

import com.hubspot.jinjava.Jinjava

class Template(templatePath: String) {
    private val template = read(templatePath)
    private val jinjava = Jinjava()

    private fun read(path: String): String {
        return ClassLoader.getSystemResourceAsStream(path)
                .bufferedReader()
                .lines()
                .reduce { f: String?, s: String? -> f + s }
                .orElse("")
    }

    fun render(mapOf: Map<String, Any>): String = jinjava.render(template, mapOf)
}