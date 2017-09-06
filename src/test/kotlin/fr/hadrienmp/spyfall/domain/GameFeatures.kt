package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.adapters.outs.SingleLocation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameFeatures {

    @Test fun `deal cards to players`() {
        val playa = Location("playa")

        var game = game(SingleLocation(playa))

        val players = somePLayers()
        for (player in players) {
            game = game.register(player)
        }

        val startedGame = game.start()

        val cards = getCards(startedGame, players)

        assertThat(cards).containsOnlyOnce(spy())
        assertThat(cards - spy()).containsOnly(Card(playa.name))
        assertThat(cards).hasSameSizeAs(players)
    }

    private fun somePLayers() = (1..3).map { Player(it.toString()) }
    private fun getCards(startedGame: StartedGame, players: List<Player>) = players.map { startedGame.cardOf(it) }
}