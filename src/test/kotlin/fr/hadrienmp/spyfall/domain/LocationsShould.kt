package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.datasources.HardCodedLocations
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LocationsShould {
    @Test fun `return random locations`() {
        val locations = HardCodedLocations()
        val uniqueLocations = (1..1000).map { locations.random() }.toSet()
        assertThat(uniqueLocations.size).isGreaterThan(1)
    }
}
