package fr.hadrienmp.spyfall.adapters.ins.web

import fr.hadrienmp.spyfall.adapters.ins.MutableGame
import fr.hadrienmp.spyfall.adapters.outs.HardCodedLocations
import fr.hadrienmp.spyfall.domain.Player
import io.javalin.Context
import io.javalin.builder.CookieBuilder

class LocationsCtrl(locations: HardCodedLocations) {

    private val locationsPage = makeTemplate(locations)

    private fun makeTemplate(locations: HardCodedLocations): String {
        val params = mapOf(Pair("locations", locations.all()))
        return Template("locations.html").render(params)
    }

    fun display(context: Context) {
        context.html(locationsPage)
    }
}

class PlayerCtrl(private val game: MutableGame) {

    private val template = Template("card.html")

    fun register(context: Context) {
        val id = context.formParam("id") ?: throw InvalidFormException()
        game.register(Player(id))
        context.cookie(CookieBuilder("id", id, path = "/"))
        context.redirect("/game")
    }

    fun displayCard(context: Context) {
        val id = context.cookie("id") ?: throw UnknownPlayerException()
        val card = game.cardOf(Player(id))
        context.html(template.render(mapOf(Pair("card", card.content))))
    }
}

class GameCtrl(private val game: MutableGame) {

    private val startGame = Template("start-game.html")
    private val register = Template("register.html")

    fun display(context: Context) {
        val gamePage = when {
            context.cookie("id") == null -> register.render()
            else -> startGame.render()
        }
        context.html(gamePage)
    }

    fun start(context: Context) {
        game.start()
        context.redirect("/player/card")
    }
}