package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.domain.Player
import fr.hadrienmp.spyfall.lib.notEmpty
import io.javalin.Context
import io.javalin.builder.CookieBuilder

fun Context.player(): Player? {
    val playerId = this.cookie("id")

    return if (playerId == null || playerId.isBlank()) null
    else Player(playerId.notEmpty())
}
fun Context.remember(player: Player) { this.cookie(CookieBuilder("id", player.id.string, path = "/")) }
fun Context.forgetPlayer() { this.removeCookie("id") }