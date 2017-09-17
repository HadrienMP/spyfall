package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.ServerDocTemplate
import fr.hadrienmp.spyfall.ui.web.testutils.containsPlayers
import fr.hadrienmp.spyfall.ui.web.testutils.displaysRegistered
import fr.hadrienmp.testutils.Doc

class PlayerRegistrationDoc : ServerDocTemplate() {

    @Doc
    fun `should register a player`() {
        val playerId = app.register()

        val gamePage = app.getGamePage(playerId)

        gamePage.displaysRegistered(true)
        gamePage.containsPlayers(1)
    }

    @Doc
    fun `should register players concurrently without loosing anyone`() {
        val numberOfPlayers = 1000
        (1..numberOfPlayers).toList()
                .parallelStream()
                .forEach { app.register() }

        val gamePage = app.getGamePage()

        gamePage.containsPlayers(numberOfPlayers)
    }
}

