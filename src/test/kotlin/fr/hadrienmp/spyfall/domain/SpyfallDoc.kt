package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.lib.HardCodedLocations
import fr.hadrienmp.spyfall.lib.notEmpty
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SpyfallDoc {

    @Test
    fun `games contain one spy`() {
        val players = listOf(
                Player("a".notEmpty()),
                Player("b".notEmpty()),
                Player("c".notEmpty()),
                Player("d".notEmpty())
        )
        val deck = Deck(players, HardCodedLocations())

        val cards = players.map(deck::cardOf)

        assertThat(cards).containsOnlyOnce(spy())
    }

    @Test
    fun `all non spy players are at the same location`() {
        val players = listOf(
                Player("a".notEmpty()),
                Player("b".notEmpty()),
                Player("c".notEmpty()),
                Player("d".notEmpty())
        )

        val deck = Deck(players, HardCodedLocations())

        val cards = players.map(deck::cardOf)

        assertThat(cards).hasSameSizeAs(players)
        assertThat((cards - spy()).toSet()).hasSize(1)
    }

    @Test
    fun `non registered players don't get cards`() {
        val deck = Deck(listOf(), HardCodedLocations())

        val card = deck.cardOf(Player("a".notEmpty()))

        assertThat(card).isNull()
    }

    @Test
    fun `a player playing alone is the spy`() {
        val player = Player("a".notEmpty())
        val deck = Deck(listOf(player), HardCodedLocations())

        val card = deck.cardOf(player)

        assertThat(card).isEqualTo(spy())
    }

    @Test
    fun `the spy is designated by random`() {
        val indicesOfSpy = (1..20).map { findSpyIndexInAGame() }.toSet()
        assertThat(indicesOfSpy.size).isGreaterThan(1)
    }

    private fun findSpyIndexInAGame(): Int {
        val players = (1..8).map { Player(it.toString().notEmpty()) }
        val deck = Deck(players, HardCodedLocations())
        val cards = players.map(deck::cardOf)
        return cards.indexOf(spy())
    }
}
