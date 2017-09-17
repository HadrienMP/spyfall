package fr.hadrienmp.spyfall.ui.web

data class Port(val value: Int) {
    companion object {
        fun fromArgs(args: Array<String>): Port = args.toList()
                .stream()
                .filter { it.matches(Regex("port=\\d+")) }
                .map { it.split("=").last().toInt() }
                .findFirst()
                .map { Port(it) }
                .orElse(default())

        fun default() = Port(8080)
    }
}