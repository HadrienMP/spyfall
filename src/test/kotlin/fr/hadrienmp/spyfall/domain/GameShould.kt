package fr.hadrienmp.spyfall.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

object GameShould {
    @Test fun `designates a spy and gives the location to other player`() {
        val location = Location("Beach")

        val players = listOf(
                Player("1"),
                Player("2"),
                Player("3"))

        val game = game(location)
                .register(players[0])
                .register(players[1])
                .register(players[2])
                .start()

        val cards = players.map { game.cardOf(it) }

        assertThat(cards).hasSize(players.size)
        assertThat(cards).containsOnlyOnce(spy())

        val nonSpyCards = cards.filter { it != spy() }
        assertThat(nonSpyCards).containsOnly(Card(location.name))
    }
}

