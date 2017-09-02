package fr.hadrienmp.spyfall.datasources

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
    )
    override fun random(): Location {
        return Location(locations.random())
    }

    private fun <E> List<E>.random(): E {
        return get(Random().nextInt(size))
    }
}

class SingleLocation(private val location: Location) : Locations {
    override fun random(): Location {
        return location
    }
}