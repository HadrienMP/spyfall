package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.*
import org.assertj.core.api.Assertions
import org.junit.Rule
import org.junit.Test

class PlayerRegistrationDoc {

    @Rule @JvmField val serverRule = ServerRule()

    @Test
    fun `should register a player`() {
        val playerId = "anId"

        register(playerId)

        val gamePage = getGamePage(playerId)

        gamePage.displaysRegistered(true)
        gamePage.containsPlayers(1)
    }

    @Test
    fun `should register players concurrently without loosing anyone`() {
        val numberOfPlayers = 1000
        (1..numberOfPlayers).map { it.toString() }
                .parallelStream()
                .forEach { register(it) }

        val gamePage = getGamePage()

        gamePage.containsPlayers(numberOfPlayers)
    }

    @Test
    fun `should not register a player with an empty id`() {
        val playerId = "  "

        val registerResponse = register(playerId)

        Assertions.assertThat(registerResponse.status()).isEqualTo(302)

        registerResponse.didNotSetCookie("id=[^;]")
    }
}