package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.ServerDocTemplate
import fr.hadrienmp.spyfall.ui.web.testutils.containsPlayers
import fr.hadrienmp.spyfall.ui.web.testutils.displaysRegistered
import org.assertj.core.api.Assertions
import fr.hadrienmp.testutils.Doc

class PlayerRegistrationDoc : ServerDocTemplate() {

    @Doc
    fun `should register a player`() {
        val playerId = "anId"

        app.register(playerId)

        val gamePage = app.getGamePage(playerId)

        gamePage.displaysRegistered(true)
        gamePage.containsPlayers(1)
    }

    @Doc
    fun `should register players concurrently without loosing anyone`() {
        val numberOfPlayers = 1000
        (1..numberOfPlayers).map { it.toString() }
                .parallelStream()
                .forEach { app.register(it) }

        val gamePage = app.getGamePage()

        gamePage.containsPlayers(numberOfPlayers)
    }

    @Doc
    fun `should not register a player with an empty id`() {
        val playerId = "  "

        val registerResponse = app.register(playerId)

        Assertions.assertThat(registerResponse.status()).isEqualTo(302)

        registerResponse.didNotSetCookie("id=[^;]")
    }
}
