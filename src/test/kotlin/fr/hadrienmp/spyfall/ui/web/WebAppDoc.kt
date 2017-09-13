package fr.hadrienmp.spyfall.ui.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class WebAppDoc {

    private var app: App? = null
    private val game = Game()

    @Before fun setUp() {
        app = App(game)
        app!!.start()
    }

    @After fun tearDown() { app!!.stop() }

    @Test fun `the game does not contain any players at start`() {
        val gamePage = getGamePage()

        gamePage.displaysRegistered(false)
        gamePage.containsPlayers(0)
    }

    @Test fun `should register a player`() {
        val playerId = "anId"

        register(playerId)

        val gamePage = getGamePage(playerId)

        gamePage.displaysRegistered(true)
        gamePage.containsPlayers(1)
    }

    @Test fun `should register players concurrently without loosing anyone`() {
        val numberOfPlayers = 1000
        (1..numberOfPlayers).map { it.toString() }
                .parallelStream()
                .forEach { register(it) }

        val gamePage = getGamePage()

        gamePage.containsPlayers(numberOfPlayers)
    }

    @Test fun `should not register a player with an empty id`() {
        val playerId = "  "

        val registerResponse = register(playerId)

        assertThat(registerResponse.status()).isEqualTo(302)

        registerResponse.didNotSetCookie("id=[^;]")
    }

    @Test fun `should start a game`() {
        register("anId")
        startGame()

        val gamePage = getGamePage()

        gamePage.gameStarted()
    }

    @Test fun `should delete the cookies of a user that is not registered`() {

        val gamePageResponse = getGamePageResponse("anId")

        gamePageResponse.didSetCookie("id=;")
    }

    @Test fun `should delete the cookies of a user with an empty id that is not registered`() {

        val gamePageResponse = getGamePageResponse("  ")

        gamePageResponse.didSetCookie("id=;")
    }

    @Test fun `should not delete the cookies of a user that is registered`() {
        val playerId = "anId"
        register(playerId)

        val gamePageResponse = getGamePageResponse(playerId)

        gamePageResponse.didNotSetCookie("id=;")
    }
}



