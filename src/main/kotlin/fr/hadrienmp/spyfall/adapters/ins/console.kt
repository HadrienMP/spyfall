package fr.hadrienmp.spyfall.adapters.ins

import fr.hadrienmp.spyfall.adapters.outs.HardCodedLocations
import fr.hadrienmp.spyfall.domain.Player
import fr.hadrienmp.spyfall.domain.game

fun main(args: Array<String>) {
    val game = MutableGame(game(HardCodedLocations()))
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

    game.start()

    println("Game started : " + game)

    players.forEach {
        val card = game.cardOf(it)
        println("Player $it received card $card")
    }
}