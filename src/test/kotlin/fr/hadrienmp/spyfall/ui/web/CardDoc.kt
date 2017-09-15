package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class CardDoc {

    @Rule @JvmField val serverRule = ServerRule()

    @Test
    fun `an unregistered player does not get a card`() {
        startGame()

        val gamePage = getGamePage("unregistered player")

        assertThat(gamePage.card()).isEmpty()
    }

    @Test
    fun `when a player is alone in the game he is the spy`() {
        register("a player")
        startGame()

        val gamePage = getGamePage("a player")

        assertThat(gamePage.card()).contains("espion")
    }
}
