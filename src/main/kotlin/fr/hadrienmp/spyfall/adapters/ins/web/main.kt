package fr.hadrienmp.spyfall.adapters.ins.web

import fr.hadrienmp.spyfall.adapters.ins.MutableGame
import fr.hadrienmp.spyfall.adapters.outs.HardCodedLocations
import fr.hadrienmp.spyfall.domain.game
import io.javalin.Javalin
import io.javalin.LogLevel


fun main(args: Array<String>) {
    val app = Javalin.create()
            .port(8080)
            .requestLogLevel(LogLevel.STANDARD)
    exceptionMapping(app)
    routes(app)
    app.start()
}

private fun routes(app: Javalin) {
    val locations = HardCodedLocations()
    val game = MutableGame(game(locations))

    val gameCtrl = GameCtrl(game)
    val playerCtrl = PlayerCtrl(game)
    val locationsCtrl = LocationsCtrl(locations)

    app.get("/") { it.redirect("/game") }
    app.get("/game", gameCtrl::display)
    app.get("/game/start", gameCtrl::start)
    app.post("/player/register", playerCtrl::register)
    app.get("/player/card", playerCtrl::displayCard)
    app.get("/locations", locationsCtrl::display)
}