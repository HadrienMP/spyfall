package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.ServerDocTemplate
import fr.hadrienmp.spyfall.ui.web.testutils.card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDoc : ServerDocTemplate() {

    @Test
    fun `an unregistered player does not get a card`() {
        app.startGame()

        val gamePage = app.getGamePage("unregistered player")

        assertThat(gamePage.card()).isEmpty()
    }

    @Test
    fun `when a player is alone in the game he is the spy`() {
        app.register("a player")
        app.startGame()

        val gamePage = app.getGamePage("a player")

        assertThat(gamePage.card()).contains("espion")
    }
}
