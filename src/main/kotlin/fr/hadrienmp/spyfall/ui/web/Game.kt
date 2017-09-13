package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.domain.Deck
import fr.hadrienmp.spyfall.domain.Player
import fr.hadrienmp.spyfall.domain.spy
import fr.hadrienmp.spyfall.lib.HardCodedLocations
import java.util.concurrent.ConcurrentLinkedDeque

class Game {
    private val players = ConcurrentLinkedDeque<Player>()
    private var deck: Deck? = null

    fun numberOfPlayers() = players.size
    fun register(player: Player) { players.add(player) }
    fun start() { deck = Deck(players, HardCodedLocations()) }
    fun isStarted() = deck != null
    fun knows(player: Player?): Boolean = players.contains(player)

    fun cardOf(player: Player?): String = when {
        player == null || deck == null -> ""
        else -> {
            val card = deck!!.cardOf(player)

            when (card) {
                null -> ""
                spy() -> "Vous êtes l'espion."
                else -> "Votre lieu : " + card.content.string
            }
        }
    }
}