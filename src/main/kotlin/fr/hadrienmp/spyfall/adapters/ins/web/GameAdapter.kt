package fr.hadrienmp.spyfall.adapters.ins.web

import fr.hadrienmp.spyfall.adapters.outs.HardCodedLocations
import fr.hadrienmp.spyfall.domain.*

class GameAdapter(locations: HardCodedLocations) {
    var startableGame: Game? = game(locations)
    var startedGame: StartedGame? = null

    fun register(player: Player) = startableGame().register(player)
    fun start() = startableGame().start()
    fun cardOf(player: Player) = startedGame().cardOf(player)
    
    fun startableGame() = startableGame ?: throw GameAlreadyStartedException()
    fun startedGame() = startedGame ?: throw GameNotStartedException()
}