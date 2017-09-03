package fr.hadrienmp.spyfall.adapters.ins.web

import fr.hadrienmp.spyfall.actors.secondaries.HardCodedLocations
import fr.hadrienmp.spyfall.domain.Game
import fr.hadrienmp.spyfall.domain.Player
import fr.hadrienmp.spyfall.domain.StartedGame
import io.javalin.Context
import io.javalin.Javalin

var game: Game? = null
var startedGame: StartedGame? = null

fun main(args: Array<String>) {
    game = Game(HardCodedLocations())

    val app = Javalin.start(8080)
    app.get("/") { it.redirect("/game") }
    app.get("/game") { it.html(gamePage(it)) }
    app.get("/game/start") { startGame(it) }
    app.post("/player/register") { register(it) }
    app.get("/player/card") { it.html(getCard(it)) }
}

private fun gamePage(context: Context): String {
    return when {
        context.cookie("id") == null -> Template("register.html").render()
        else -> Template("start-game.html").render()
    }
}

fun register(context: Context) {
    val id = context.formParam("id") ?: throw InvalidFormException()
    game().register(Player(id))
    context.cookie("id", id)
    context.redirect("/game")
}

fun startGame(context: Context) {
    startedGame = game().start()
    game = null
    context.redirect("/player/card")
}

fun getCard(context: Context): String {
    val id = context.cookie("id") ?: throw UnknownPlayerException()
    val card = startedGame().cardOf(Player(id))
    return Template("card.html").render(mapOf(Pair("card", card.content)))
}

private fun game() = game ?: throw GameAlreadyStartedException()
private fun startedGame() = startedGame ?: throw GameNotStartedException()