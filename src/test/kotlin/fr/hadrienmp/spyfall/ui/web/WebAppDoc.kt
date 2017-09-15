package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.*
import org.junit.Rule
import org.junit.Test

class WebAppDoc {

    @Rule @JvmField val serverRule = ServerRule()

    @Test fun `the game does not contain any players at start`() {
        val gamePage = getGamePage()

        gamePage.displaysRegistered(false)
        gamePage.containsPlayers(0)
    }

    @Test fun `should start a game`() {
        register("anId")
        startGame()

        val gamePage = getGamePage()

        gamePage.gameStarted()
    }
}



