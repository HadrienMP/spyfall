package fr.hadrienmp.spyfall.domain

import java.util.*

fun spy() = Card("Spy")
data class Card(val content: String)

fun deck(numberOfCards: Int, location: Location): List<Card> {
    val numberOfLocationCards = numberOfCards - 1
    val locationCards = (0 until numberOfLocationCards).map { Card(location.name) }
    val deck = locationCards + spy()
    Collections.shuffle(deck)
    return deck
}