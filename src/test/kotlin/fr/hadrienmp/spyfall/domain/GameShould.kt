package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.adapters.outs.SingleLocation
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class GameShould {

    @Test(expected = AlreadyRegistered::class)
    fun `prevent players from registering twice`() {
        val player = Player("id")

        aGame().register(player).register(player)
    }

    @Test
    fun `not deal cards to unknown players`() {
        val players = (1..3).map { Player(it.toString()) }
        val game = startGameWith(players)
        val unknownPlayer = Player("a random player")
        assertThatThrownBy { game.cardOf(unknownPlayer) }
                .isInstanceOf(UnknownPLayerException::class.java)
    }

    @Test
    fun `deal cards at random`() {
        val indicesOfSpy = (1..1000).map { indexOfSpyInAGane() }.toSet()
        assertThat(indicesOfSpy.size).isGreaterThan(1)
    }

    @Test
    fun `be able to start without players`() {
        val players = listOf<Player>()
        val game = startGameWith(players)
        assertThat(game).isNotNull()
    }

    private fun indexOfSpyInAGane(): Int {
        val players = (1..10).map { Player(it.toString()) }
        val game = startGameWith(players)
        return players.map { game.cardOf(it) }.indexOf(spy())
    }

    private fun startGameWith(players: List<Player>): StartedGame {
        var game = aGame()
        for (player in players) {
            game = game.register(player)
        }

        val startedGame = game.start()
        return startedGame
    }

    private fun aGame() = game(SingleLocation(Location("playa")))
}
