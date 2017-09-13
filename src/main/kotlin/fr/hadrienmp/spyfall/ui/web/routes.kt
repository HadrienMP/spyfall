package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.domain.Player
import fr.hadrienmp.spyfall.lib.notEmpty
import io.javalin.Javalin

val gameUrl = "/game"

fun routes(app: Javalin, game: Game) {

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

    app.post("/player/register") {
        val playerId = it.formParam("id")
        if (playerId == null || playerId.isBlank()) {
            it.redirect(gameUrl)
        } else {
            val player = Player(playerId.notEmpty())
            game.register(player)
            it.remember(player)
            it.redirect(gameUrl)
        }
    }
}