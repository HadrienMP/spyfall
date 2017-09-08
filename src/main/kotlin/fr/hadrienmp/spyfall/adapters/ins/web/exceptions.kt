package fr.hadrienmp.spyfall.adapters.ins.web

import io.javalin.Javalin
import mu.KotlinLogging

class UnknownPlayerException : Exception()
class InvalidFormException : Exception()
class GameAlreadyStartedException : Exception()
class GameNotStartedException : Exception()

fun exceptionMapping(app: Javalin) {
    val logger = KotlinLogging.logger("Web Exception")

    app.exception(UnknownPlayerException::class.java) { e, ctx ->
        logger.warn {e.javaClass.simpleName}

        ctx.cookieMap().forEach { ctx.removeCookie(it.key) }
        ctx.redirect("/game")
    }
    app.exception(InvalidFormException::class.java) { e, ctx ->
        logger.warn {e.javaClass.simpleName}
        ctx.status(400)
    }
    app.exception(GameAlreadyStartedException::class.java) { e, ctx ->
        logger.warn {e.javaClass.simpleName}

        ctx.status(400)
        ctx.result("Désolé, la partie est déja démarrée.")
    }
    app.exception(GameNotStartedException::class.java) { e, ctx ->
        logger.warn {e.javaClass.simpleName}

        ctx.cookieMap().forEach { ctx.removeCookie(it.key) }
        ctx.redirect("/game")
    }
}
