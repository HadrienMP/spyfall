package fr.hadrienmp.spyfall.ui

import fr.hadrienmp.spyfall.domain.Deck
import fr.hadrienmp.spyfall.domain.Player
import fr.hadrienmp.spyfall.lib.notEmpty
import fr.hadrienmp.spyfall.lib.HardCodedLocations

fun main(vararg args: String) {
    println("""
._.   _________              ___________      .__  .__    ._.
| |  /   _____/_____ ___.__. \_   _____/____  |  | |  |   | |
| |  \_____  \\____ <   |  |  |    __) \__  \ |  | |  |   | |
 \|  /        \  |_> >___  |  |     \   / __ \|  |_|  |__  \|
 __ /_______  /   __// ____|  \___  /  (____  /____/____/  __
 \/         \/|__|   \/           \/        \/             \/


    """.trimIndent())

    val players = listOf(
            Player("Aaron".notEmpty()),
            Player("Charlene".notEmpty()),
            Player("Tabata".notEmpty())
    )

    val deck = Deck(players, HardCodedLocations())

    players.forEach {
        val card = deck.cardOf(it)
        println("$it : $card")
    }
}