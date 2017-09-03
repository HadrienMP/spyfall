package fr.hadrienmp.spyfall.adapters.ins

import fr.hadrienmp.spyfall.adapters.outs.HardCodedLocations
import fr.hadrienmp.spyfall.domain.Game
import fr.hadrienmp.spyfall.domain.Player

fun main(args: Array<String>) {
    val game = Game(HardCodedLocations())
    println("Game created : " + game)

    val players = listOf(
            Player("Q"),
            Player("W"),
            Player("E")
    )

    players.forEach {
        game.register(it)
        println("Player registered : " + it)
    }

    val startedGame = game.start()

    println("Game started : " + startedGame)

    players.forEach {
        val card = startedGame.cardOf(it)
        println("Player $it received card $card")
    }
}