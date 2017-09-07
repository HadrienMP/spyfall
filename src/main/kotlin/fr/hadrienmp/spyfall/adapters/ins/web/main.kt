package fr.hadrienmp.spyfall.adapters.ins.web

import fr.hadrienmp.spyfall.adapters.ins.MutableGame
import fr.hadrienmp.spyfall.adapters.outs.HardCodedLocations
import fr.hadrienmp.spyfall.domain.game
import io.javalin.Javalin


fun main(args: Array<String>) {
    val locations = HardCodedLocations()
    val game = MutableGame(game(locations))

    val gameCtrl = GameCtrl(game)
    val player = PlayerCtrl(game)
    val locationsCtrl = LocationsCtrl(locations)

    val app = Javalin.start(8080)
    app.get("/") { it.redirect("/game") }
    app.get("/game", gameCtrl::display)
    app.get("/game/start", gameCtrl::start)
    app.post("/player/register", player::register)
    app.get("/player/card", player::displayCard)
    app.get("/locations", locationsCtrl::display)
}

