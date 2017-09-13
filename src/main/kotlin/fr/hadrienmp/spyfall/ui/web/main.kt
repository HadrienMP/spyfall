package fr.hadrienmp.spyfall.ui.web

import io.javalin.Javalin
import io.javalin.LogLevel


fun main(args: Array<String>) {
    App(Game()).start()
}

class App(private val game: Game) {
    private val app = createApp()

    private fun createApp(): Javalin {
        val app = Javalin.create()
                .port(8080)
                .requestLogLevel(LogLevel.STANDARD)
        routes(app, game)
        return app
    }

    fun start() { app.start() }
    fun stop() { app.stop() }
}

