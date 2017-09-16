package fr.hadrienmp.spyfall.ui.web.testutils

import fr.hadrienmp.spyfall.ui.web.Port
import org.assertj.core.api.Assertions.assertThat
import fr.hadrienmp.spyfall.ui.web.testutils.Doc

class PortStoreDoc {

    @Doc fun `should return default port when first called`() {
        val freePort = PortStore().get()
        assertThat(freePort).isEqualTo(Port.default())
    }

    @Doc fun `should return different ports each time it is called`() {
        val portStore = PortStore()
        val nbPortsWanted = 10

        val ports = (1..nbPortsWanted).map { portStore.get() }.toSet()

        assertThat(ports).hasSize(nbPortsWanted)
    }

    @Doc fun `should return freed port`() {
        // GIVEN
        val portStore = PortStore()
        val freedPort = portStore.get()
        portStore.free(freedPort)

        // WHEN
        val port = portStore.get()

        // THEN
        assertThat(port).isEqualTo(freedPort)
    }

    @Doc fun `should not return a used port after freeing one`() {
        // GIVEN
        val portStore = PortStore()

        val freedPort = portStore.get()
        val usedPort = portStore.get()
        portStore.free(freedPort)
        portStore.get()

        // WHEN
        val port = portStore.get()

        // THEN
        assertThat(port).isNotEqualTo(usedPort)
    }
}