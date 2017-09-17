package fr.hadrienmp.spyfall.ui.web.testutils

data class PlayerIdCookie(val id: String) {
    companion object {
        private val idCookieFormat = "id=%s;"
        private val regex = Regex(idCookieFormat.format("([^;]*)"))

        fun empty(): PlayerIdCookie {
            return PlayerIdCookie("")
        }

        fun from(cookies: List<String>): PlayerIdCookie {
            val id = cookies.map { regex.find(it)?.groupValues }
                    .filterNot { it == null || it.size < 2 }
                    .map { it!![1] }
                    .first()
            return PlayerIdCookie(id)
        }
    }

    fun toRegex(): String {
        return idCookieFormat.format(id)
    }
}