package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.ServerDocTemplate
import fr.hadrienmp.spyfall.ui.web.testutils.containsPlayers
import fr.hadrienmp.spyfall.ui.web.testutils.displaysRegistered
import fr.hadrienmp.spyfall.ui.web.testutils.gameStarted
import org.junit.jupiter.api.Test

class WebAppDoc : ServerDocTemplate() {

    @Test fun `the game does not contain any players at start`() {
        val gamePage = app.getGamePage()

        gamePage.displaysRegistered(false)
        gamePage.containsPlayers(0)
    }

    @Test fun `should start a game`() {
        app.register("anId")
        app.startGame()

        val gamePage = app.getGamePage()

        gamePage.gameStarted()
    }
}



