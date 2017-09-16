package fr.hadrienmp.spyfall.lib

import org.assertj.core.api.Assertions.assertThat
import fr.hadrienmp.testutils.Doc

class HardCodedLocationsDoc {
    @Doc
    fun `should give random locations`() {
        val hardCodedLocations = HardCodedLocations()
        val locations = (1..100).map { hardCodedLocations.random() }.toSet()
        assertThat(locations.size).isGreaterThan(1)
    }
}