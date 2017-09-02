package fr.hadrienmp.spyfall.domain

import java.util.*

class Deck(numberOfPlayers: Int, location: Location) {
    private val cards: List<Card>

    init {
        val range = 0..(numberOfPlayers - 1)
        val cards = range.map { Card(location.name) }.toMutableList()
        cards[0] = spy()
        Collections.shuffle(cards)
        this.cards = cards.map { it }
    }

    fun deal(players: List<Player>): Map<Player, Card> {
        val iterator = cards.iterator()
        // todo fragile
        return players.map { Pair(it, iterator.next()) }.toMap()
    }
}