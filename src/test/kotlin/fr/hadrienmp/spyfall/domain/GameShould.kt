package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.datasources.Location
import fr.hadrienmp.spyfall.datasources.SingleLocation
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class GameShould {

    @Test(expected = AlreadyRegistered::class)
    fun `prevent players from registering twice`() {
        val game = aGame()
        val player = Player("id")
        game.register(player)
        game.register(player)
    }

    @Test
    fun `register players in parallel without loosing players`() {
        val game = aGame()
        val players = (1..1000).map { Player(it.toString()) }

        players.parallelStream().forEach { game.register(it) }

        assertThat(game.registered()).containsOnlyElementsOf(players)
    }

    @Test
    fun `not start a game with less than 3 players`() {
        val game = aGame()
        assertThatThrownBy { game.start() }
                .isInstanceOf(NotEnoughPlayersException::class.java)
                .hasMessage("The game cannot be started with 0 players, 3 is the minimum")
    }

    @Test
    fun `not deal cards to unkown players`() {
        val game = aGame()
        (1..3).map { Player(it.toString()) }
                .forEach { game.register(it) }

        val unknownPlayer = Player("a random player")

        assertThatThrownBy { game.start().cardOf(unknownPlayer) }
                .isInstanceOf(UnkownPLayerException::class.java)
    }

    private fun aGame() =  Game(SingleLocation(Location("playa")))
}

