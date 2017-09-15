package fr.hadrienmp.spyfall.ui.web.testutils

import fr.hadrienmp.spyfall.ui.web.Port

class PortStore {
    private var used: MutableSet<Port> = mutableSetOf()

    fun get(): Port {
        val freePort = findFreePort()
        used.add(freePort)
        return freePort
    }

    private fun findFreePort(): Port {
        var port = Port.default()
        while (used.contains(port)) {
            port = Port(port.value + 1)
        }
        return port
    }

    fun free(port: Port) {
        used.remove(port)
    }
}