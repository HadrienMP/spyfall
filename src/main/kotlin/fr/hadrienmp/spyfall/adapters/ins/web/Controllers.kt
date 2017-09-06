package fr.hadrienmp.spyfall.adapters.ins.web

import fr.hadrienmp.spyfall.adapters.outs.HardCodedLocations
import fr.hadrienmp.spyfall.domain.Player
import io.javalin.Context

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

class PlayerCtrl(private val game: GameAdapter) {

    fun register(context: Context) {
        val id = context.formParam("id") ?: throw InvalidFormException()
        game.register(Player(id))
        context.cookie("id", id)
        context.redirect("/game")
    }

    fun displayCard(context: Context) {
        val id = context.cookie("id") ?: throw UnknownPlayerException()
        val card = game.cardOf(Player(id))
        context.html(Template("card.html").render(mapOf(Pair("card", card.content))))
    }
}

class GameCtrl(private val game: GameAdapter) {

    fun display(context: Context) {
        val gamePage = when {
            context.cookie("id") == null -> Template("register.html").render()
            else -> Template("start-game.html").render()
        }
        context.html(gamePage)
    }

    fun start(context: Context) {
        game.start()
        context.redirect("/player/card")
    }
}