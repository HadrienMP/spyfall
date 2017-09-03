package fr.hadrienmp.spyfall.adapters.outs

import fr.hadrienmp.spyfall.domain.Location
import fr.hadrienmp.spyfall.domain.Locations
import java.util.*

class HardCodedLocations: Locations {
    private val locations = listOf(
            "Playa",
            "Office",
            "Club",
            "House",
            "Cocktail Party"
    ).map { Location(it) }

    override fun random() = locations.random()
    override fun all() = locations
    private fun <E> List<E>.random() = get(Random().nextInt(size))
    override fun toString(): String {
        return HardCodedLocations::class.java.simpleName + " " +
                "{locations = $locations}"
    }
}

class SingleLocation(private val location: Location) : Locations {
    override fun random() = location
    override fun all() = listOf(location)
}