package fr.hadrienmp.spyfall.ui.web.testutils

import org.junit.jupiter.api.AfterEach

abstract class ServerDocTemplate {
    val app = TestServer()

    @AfterEach
    fun stop() {
        app.stop()
    }
}