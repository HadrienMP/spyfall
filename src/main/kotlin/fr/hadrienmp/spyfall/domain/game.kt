package fr.hadrienmp.spyfall.domain

fun game(location: Location): RegistrableGame {
    return RegistrableGame(listOf(), location)
}

open class RegistrableGame(val players: List<Player>, val location: Location) {
    fun register(player: Player): StartableGame {
        return StartableGame(players + player, location)
    }
}

class StartableGame(players: List<Player>, location: Location): RegistrableGame(players, location) {
    fun start(): StartedGame {
        return StartedGame(players, location)
    }
}

class StartedGame(players: List<Player>, location: Location) {

    private val playersCards: Map<Player, Card>

    init {
        val numberOfPlayers = players.size
        val deck = Deck(numberOfPlayers, location)
        playersCards = deck.deal(players)
    }

    fun cardOf(player: Player): Card? {
        return playersCards[player]
    }
}

