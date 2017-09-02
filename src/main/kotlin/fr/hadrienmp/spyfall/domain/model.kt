package fr.hadrienmp.spyfall.domain

fun spy() = Card("Spy")
data class Card(val content: String)
data class Player(val id: String)
data class Location(val name: String)