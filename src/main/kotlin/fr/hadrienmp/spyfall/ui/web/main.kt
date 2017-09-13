package fr.hadrienmp.spyfall.ui.web

import io.javalin.Javalin
import io.javalin.LogLevel


fun main(args: Array<String>) {
    App(Port.fromArgs(args), Game()).start()
}

class App(port:Port, game: Game) {
    private val app: Javalin

    init {
        val app = Javalin.create()
                .port(port.value)
                .requestLogLevel(LogLevel.STANDARD)
        routes(app, game)
        this.app = app
    }

    fun start() { app.start() }
    fun stop() { app.stop() }
}

data class Port(val value: Int) {
    companion object {
        fun fromArgs(args: Array<String>): Port = args.toList()
                .stream()
                .filter { it.matches(Regex("port=\\d+")) }
                .map { it.split("=").last().toInt() }
                .findFirst()
                .map { Port(it) }
                .orElse(default())

        fun default() = Port(8080)
    }
}
