package fr.hadrienmp.spyfall.ui.web

import fr.hadrienmp.spyfall.ui.web.testutils.PlayerIdCookie
import fr.hadrienmp.spyfall.ui.web.testutils.ServerDocTemplate
import fr.hadrienmp.spyfall.ui.web.testutils.doesNotSetCookie
import fr.hadrienmp.spyfall.ui.web.testutils.setsCookie
import fr.hadrienmp.testutils.Doc

class PlayerRemembranceDoc : ServerDocTemplate() {

    @Doc
    fun `should delete the cookies of a user that is not registered`() {

        val gamePageResponse = app.getGamePageResponse("anId")

        gamePageResponse.setsCookie(PlayerIdCookie.empty())
    }

    @Doc
    fun `should delete the cookies of a user with an empty id that is not registered`() {

        val gamePageResponse = app.getGamePageResponse("  ")

        gamePageResponse.setsCookie(PlayerIdCookie.empty())
    }

    @Doc
    fun `should not delete the cookies of a user that is registered`() {
        val playerId = app.register()

        val gamePageResponse = app.getGamePageResponse(playerId)

        gamePageResponse.doesNotSetCookie(PlayerIdCookie.empty())
    }
}

