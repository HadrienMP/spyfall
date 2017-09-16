package fr.hadrienmp.spyfall.ui.web.testutils

import org.assertj.core.api.Assertions.assertThat
import org.jsoup.nodes.Document

fun Document.displaysRegistered(registered: Boolean) {
    assertThat(this.select("body").hasClass("registered"))
            .`as`("Expected registration status displayed")
            .isEqualTo(registered)
}

fun Document.containsPlayers(numberOfPlayers: Int) {
    assertThat(this.select("#numberOfPlayers").text())
            .`as`("Expected number of players displayed")
            .contains("$numberOfPlayers joueur")
}

fun Document.gameStarted(started: Boolean) {
    assertThat(this.select("body").hasClass("started"))
            .`as`("Expected game to be started")
            .isEqualTo(started)
}

fun Document.card(): String {
    return this.select("#card").text()
}

fun Document.displaysStopButton() {
    assertThat(this.select("button#stop").text()).contains("Arrêter")
}