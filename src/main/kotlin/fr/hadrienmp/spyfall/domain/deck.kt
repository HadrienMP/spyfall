package fr.hadrienmp.spyfall.domain

import fr.hadrienmp.spyfall.datasources.Location

fun spy() = Card("Spy")
data class Card(val content: String)

fun deck(numberOfCards: Int, location: Location): List<Card> {
    val numberOfLocationCards = numberOfCards - 1
    val locationCards = (0 until numberOfLocationCards).map { Card(location.name) }
    return locationCards + spy()
}