package fr.hadrienmp.spyfall.ui.web.testutils

import com.jcabi.http.Response
import org.assertj.core.api.Assertions
import javax.ws.rs.core.HttpHeaders


fun Response.doesNotSetCookie(cookie: PlayerIdCookie) {
    val matchingCookies = findSetCookieMatching(cookie.toRegex())
    Assertions.assertThat(matchingCookies).isEmpty()
}

fun Response.setsCookie(cookie: PlayerIdCookie) {
    val matchingCookies = findSetCookieMatching(cookie.toRegex())
    Assertions.assertThat(matchingCookies).isNotEmpty()
}

private fun Response.findSetCookieMatching(regex: String): List<String> {
    val cookies = this.headers()[HttpHeaders.SET_COOKIE].orEmpty()
    return cookies.filter { Regex(regex).containsMatchIn(it) }
}