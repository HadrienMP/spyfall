package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.*
import fr.hadrienmp.testutils.Doc

class WebAppDoc : ServerDocTemplate() {

    @Doc
    fun `the game does not contain any players at start`() {
        val gamePage = app.getGamePage()

        gamePage.displaysRegistered(false)
        gamePage.containsPlayers(0)
    }

    @Doc
    fun `a game can be started`() {
        app.register()
        app.startGame()

        val gamePage = app.getGamePage()

        gamePage.gameStarted(true)
    }

    @Doc
    fun `a started game has a stop button`() {
        app.register()
        app.startGame()
        app.getGamePage().displaysStopButton()
    }

    @Doc
    fun `stopping a game displays the registration page`() {
        val playerId = "anId"
        app.register()
        app.startGame()
        app.stopGame()

        val gamePage = app.getGamePage(playerId)

        gamePage.displaysRegistered(false)
        gamePage.containsPlayers(0)
        gamePage.gameStarted(false)
    }
}



