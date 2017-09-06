package fr.hadrienmp.spyfall.domain

data class Player(val id: String)

fun game(locations: Locations): Game {
    return Game(listOf(), locations)
}

class Game(private val players: List<Player>, private val locations: Locations) {
    fun register(player: Player): Game {
        if (players.contains(player)) {
            throw AlreadyRegistered()
        }
        return Game(players + player, locations)
    }

    fun start(): StartedGame {
        return StartedGame(players, locations)
    }
}

class StartedGame(players: List<Player>, locations: Locations) {
    private val playersCards: Map<Player, Card>

    init {
        val numberOfCards = players.size
        val deck = deck(numberOfCards, locations.random())
        playersCards = (0 until numberOfCards).map { Pair(players[it], deck[it]) }.toMap()
    }

    fun cardOf(player: Player): Card {
        return playersCards.getOrElse(player) { throw UnknownPLayerException() }
    }
}