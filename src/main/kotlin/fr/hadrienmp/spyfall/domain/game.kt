package fr.hadrienmp.spyfall.domain

import java.util.concurrent.ConcurrentLinkedDeque

data class Player(val id: String)

class Game(locations: Locations) {

    private val players: MutableCollection<Player> = ConcurrentLinkedDeque()
    private val location = locations.random()

    fun register(player: Player) {
        if (players.contains(player)) {
            throw AlreadyRegistered()
        }

        players.add(player)
    }

    fun registered() = players.toList()
    fun start() = StartedGame(players.toList(), location)
}

class StartedGame(players: List<Player>, location: Location) {
    private val minimumNumberOfPlayers = 3
    private val playersCards: Map<Player, Card>

    init {
        if (players.size < minimumNumberOfPlayers)
            throw NotEnoughPlayersException(players.size, minimumNumberOfPlayers)

        val numberOfCards = players.size
        val deck = deck(numberOfCards, location)
        playersCards = (0 until numberOfCards).map { Pair(players[it], deck[it]) }.toMap()
    }

    fun cardOf(player: Player): Card {
        return playersCards.getOrElse(player) { throw UnknownPLayerException() }
    }
}