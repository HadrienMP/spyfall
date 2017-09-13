package fr.hadrienmp.spyfall.lib

import fr.hadrienmp.spyfall.domain.Locations
import java.util.*

class HardCodedLocations: Locations {
    private val locations = listOf("Beach", "Office", "Airport")

    override fun random() = locations.randomPick()

    private fun List<String>.randomPick(): String {
        val mutableLocations = this.toMutableList()
        Collections.shuffle(mutableLocations)
        return mutableLocations[0]
    }
}