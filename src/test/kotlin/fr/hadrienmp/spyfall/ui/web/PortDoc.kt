package fr.hadrienmp.spyfall.ui.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PortDoc {

    @Test fun `should create port from argument list`() {
        val expectedPort = 1234

        val port = Port.fromArgs(arrayOf("port=$expectedPort"))

        assertThat(port.value).isEqualTo(expectedPort)
    }

    @Test fun `should return the default port for an empty list`() {
        val port = Port.fromArgs(arrayOf())

        assertThat(port).isEqualTo(Port.default())
    }

    @Test fun `should return the default port for an empty port`() {
        val port = Port.fromArgs(arrayOf("port="))

        assertThat(port).isEqualTo(Port.default())
    }

    @Test fun `should return the default port for a port that is not a number`() {
        val port = Port.fromArgs(arrayOf("port=dfsf"))

        assertThat(port).isEqualTo(Port.default())
    }

    @Test fun `should return the default port for a negative number`() {
        val port = Port.fromArgs(arrayOf("port=-14"))

        assertThat(port).isEqualTo(Port.default())
    }
}