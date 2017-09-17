package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.domain.Player
import fr.hadrienmp.spyfall.lib.notEmpty
import io.javalin.Javalin
import io.javalin.LogLevel
import java.util.*

class App(port: Port, private val game: Game) {
    private val app: Javalin
    private val gameUrl = "/game"

    init {
        val app = Javalin.create()
                .port(port.value)
                .requestLogLevel(LogLevel.STANDARD)
        this.app = app
        routes()
        app.start()
    }

    fun stop() { app.stop() }


    private fun routes() {

        app.before { if (!game.knows(it.player())) it.forgetPlayer() }

        app.get("/") { it.redirect(gameUrl) }

        app.get(gameUrl) {

            val templatePath = when {
                game.isStarted() -> "startedGame.html"
                game.knows(it.player()) -> "registered.html"
                else -> "unregistered.html"
            }

            val params = mapOf(
                    Pair("number_of_players", game.numberOfPlayers()),
                    Pair("card", game.cardOf(it.player()))
            )
            it.html(Template(templatePath).render(params))
        }

        app.post("/game/start") {
            game.start()
            it.redirect(gameUrl)
        }

        app.post("/game/stop") {
            game.reset()
            it.redirect(gameUrl)
        }

        app.post("/player/register") {
            val player = Player(UUID.randomUUID().toString().notEmpty())
            game.register(player)
            it.remember(player)
            it.redirect(gameUrl)
        }
    }
}