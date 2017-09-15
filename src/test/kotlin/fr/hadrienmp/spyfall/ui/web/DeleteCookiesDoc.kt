package fr.hadrienmp.spyfall.ui.web

import com.jcabi.http.Response
import fr.hadrienmp.spyfall.ui.web.testutils.*
import org.assertj.core.api.Assertions
import org.junit.Rule
import org.junit.Test
import javax.ws.rs.core.HttpHeaders

class DeleteCookiesDoc {

    @Rule @JvmField val serverRule = ServerRule()

    @Test
    fun `should delete the cookies of a user that is not registered`() {

        val gamePageResponse = getGamePageResponse("anId")

        gamePageResponse.didSetCookie("id=;")
    }

    @Test
    fun `should delete the cookies of a user with an empty id that is not registered`() {

        val gamePageResponse = getGamePageResponse("  ")

        gamePageResponse.didSetCookie("id=;")
    }

    @Test
    fun `should not delete the cookies of a user that is registered`() {
        val playerId = "anId"
        register(playerId)

        val gamePageResponse = getGamePageResponse(playerId)

        gamePageResponse.didNotSetCookie("id=;")
    }
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