package fr.hadrienmp.spyfall.ui.web.testutils

import org.assertj.core.api.Assertions
import org.jsoup.nodes.Document

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

fun Document.card(): String {
    return this.select("#card").text()
}