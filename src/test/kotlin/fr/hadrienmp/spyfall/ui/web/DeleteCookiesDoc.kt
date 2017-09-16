package fr.hadrienmp.spyfall.ui.web

import com.jcabi.http.Response
import fr.hadrienmp.spyfall.ui.web.testutils.ServerDocTemplate
import org.assertj.core.api.Assertions
import fr.hadrienmp.spyfall.ui.web.testutils.Doc
import javax.ws.rs.core.HttpHeaders

class DeleteCookiesDoc : ServerDocTemplate() {

    @Doc
    fun `should delete the cookies of a user that is not registered`() {

        val gamePageResponse = app.getGamePageResponse("anId")

        gamePageResponse.didSetCookie("id=;")
    }

    @Doc
    fun `should delete the cookies of a user with an empty id that is not registered`() {

        val gamePageResponse = app.getGamePageResponse("  ")

        gamePageResponse.didSetCookie("id=;")
    }

    @Doc
    fun `should not delete the cookies of a user that is registered`() {
        val playerId = "anId"
        app.register(playerId)

        val gamePageResponse = app.getGamePageResponse(playerId)

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