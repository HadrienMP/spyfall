package fr.hadrienmp.spyfall.adapters.ins

import fr.hadrienmp.spyfall.adapters.ins.web.GameAlreadyStartedException
import fr.hadrienmp.spyfall.adapters.ins.web.GameNotStartedException
import fr.hadrienmp.spyfall.domain.*

class MutableGame(game: Game) {
    var startableGame: Game? = game
    var startedGame: StartedGame? = null

    fun register(player: Player) { startableGame = startableGame().register(player) }
    fun start() { startedGame = startableGame().start() }
    fun cardOf(player: Player) = startedGame().cardOf(player)

    fun startableGame() = startableGame ?: throw GameAlreadyStartedException()
    fun startedGame() = startedGame ?: throw GameNotStartedException()
}