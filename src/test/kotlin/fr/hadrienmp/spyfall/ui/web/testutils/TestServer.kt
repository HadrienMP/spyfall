package fr.hadrienmp.spyfall.ui.web.testutils

import com.jcabi.http.Request
import com.jcabi.http.Response
import com.jcabi.http.request.JdkRequest
import fr.hadrienmp.spyfall.ui.web.App
import fr.hadrienmp.spyfall.ui.web.Game
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.ws.rs.core.HttpHeaders

class TestServer {
    companion object {
        private val portStore = PortStore()
    }
    private val port = portStore.get()
    private val app = App(port, Game())
    private val serverUrl = "http://localhost:${port.value}"

    init { app.start() }

    fun getGamePage() = getGamePage(null)
    fun getGamePage(playerId: String?): Document {
        val gamePageResponse = getGamePageResponse(playerId)

        val gamePageHtml = gamePageResponse.body()

        return Jsoup.parse(gamePageHtml)
    }

    fun getGamePageResponse(playerId: String?): Response {
        var request: Request = JdkRequest("$serverUrl/game")

        if (playerId != null) {
            request = request.header(HttpHeaders.COOKIE, "id=$playerId")
        }

        val gamePageResponse = request.fetch()
        return gamePageResponse
    }

    fun register(playerId: String): Response {
        return JdkRequest("$serverUrl/player/register")
                .method(Request.POST)
                .body()
                .formParam("id", playerId)
                .back()
                .fetch()
    }

    fun startGame() {
        JdkRequest("$serverUrl/game/start")
                .method(Request.POST)
                .fetch()
                .body()
    }

    fun stop() {
        app.stop()
        portStore.free(port)
    }
}