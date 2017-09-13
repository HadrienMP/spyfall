package fr.hadrienmp.spyfall.lib

import fr.hadrienmp.spyfall.domain.Locations
import java.util.*

class HardCodedLocations: Locations {
    private val locations = listOf(
            "Ambassade",
            "Avion",
            "Banque",
            "Base militaire",
            "Bateau pirate",
            "Boite de nuit",
            "Carnaval",
            "Casino",
            "Cirque",
            "Croisades",
            "École",
            "Fête d'entreprise",
            "Garage",
            "Hôpital",
            "Hôtel",
            "Paquebot",
            "Parc d'attraction",
            "Plage",
            "Poste de police",
            "Restaurant",
            "Sous-marin",
            "Station polaire",
            "Station spatiale",
            "Studio de cinéma",
            "Supermarché",
            "Centre de thalasso",
            "Théatre",
            "Train de voyageurs",
            "Université",
            "Zoo"
    )

    override fun random() = locations.randomPick()

    private fun List<String>.randomPick(): String {
        val mutableLocations = this.toMutableList()
        Collections.shuffle(mutableLocations)
        return mutableLocations[0]
    }
}