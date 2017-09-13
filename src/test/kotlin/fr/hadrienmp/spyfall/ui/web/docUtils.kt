package fr.hadrienmp.spyfall.ui.web

import com.jcabi.http.Request
import com.jcabi.http.Response
import com.jcabi.http.request.JdkRequest
import org.assertj.core.api.Assertions
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.ws.rs.core.HttpHeaders

private val serverUrl = "http://localhost:8080"

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

fun Document.displaysRegistered(registered: Boolean) {
    Assertions.assertThat(this.select("body").hasClass("registered"))
            .`as`("Expected registration status displayed")
            .isEqualTo(registered)
}

fun Document.containsPlayers(numberOfPlayers: Int) {
    Assertions.assertThat(this.select("#numberOfPlayers").text())
            .`as`("Expected number of players displayed")
            .contains("$numberOfPlayers joueur")
}

fun Document.gameStarted() {
    Assertions.assertThat(this.select("body").hasClass("started"))
            .`as`("Expected game to be started")
            .isTrue()
}


fun Response.didNotSetCookie(regex: String) {
    val matchingCookies = findSetCookieMatching(regex)
    Assertions.assertThat(matchingCookies).isEmpty()
}

fun Response.didSetCookie(regex: String) {
    val matchingCookies = findSetCookieMatching(regex)
    Assertions.assertThat(matchingCookies).isNotEmpty()
}

private fun Response.findSetCookieMatching(regex: String): List<String> {
    val cookies = this.headers()[HttpHeaders.SET_COOKIE].orEmpty()
    return cookies.filter { Regex(regex).containsMatchIn(it) }
}