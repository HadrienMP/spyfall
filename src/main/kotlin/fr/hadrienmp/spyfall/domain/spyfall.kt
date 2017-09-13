package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.lib.NotEmptyString
import fr.hadrienmp.spyfall.lib.notEmpty
import java.util.*


data class Player(val id: NotEmptyString) {
    override fun toString(): String {
        return "Player[${id.string}]"
    }
}

interface Locations {
    fun random(): String
}

class Deck(players: Collection<Player>, locations: Locations) {

    private val playersCards = deal(players, cards(players.size, locations))

    private fun deal(players: Iterable<Player>, cards: Iterable<Card>): Map<Player, Card> {
        val cardsIterator = cards.iterator()
        return players.map { Pair(it, cardsIterator.next()) }.toMap()
    }

    private fun cards(numberOfPlayers: Int, locations: Locations): Iterable<Card> {
        val card = Card(locations.random().notEmpty())
        val regularPlayerCards = (1 until numberOfPlayers).map { card }
        val cards = regularPlayerCards + spy()
        Collections.shuffle(cards)
        return cards
    }

    fun cardOf(player: Player): Card? {
        return playersCards[player]
    }
}

fun spy() = Card("Spy".notEmpty())
data class Card(val content: NotEmptyString) {
    override fun toString(): String {
        return "Card[${content.string}]"
    }
}