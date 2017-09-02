package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.datasources.SingleLocation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameFeatures {

    @Test fun `deal cards to players`() {
        val playa = Location("playa")

        val game = Game(SingleLocation(playa))

        val players = registerSomePLayers(game)

        val startedGame = game.start()

        val cards = getCards(startedGame, players)

        assertThat(cards).containsOnlyOnce(spy())
        assertThat(cards - spy()).containsOnly(Card(playa.name))
        assertThat(cards).hasSameSizeAs(players)
    }

    private fun registerSomePLayers(game: Game): List<Player> {
        val players = somePLayers()
        players.map { game.register(it) }
        return players
    }

    private fun somePLayers() = (1..3).map { Player(it.toString()) }
    private fun getCards(startedGame: StartedGame, players: List<Player>) = players.map { startedGame.cardOf(it) }
}