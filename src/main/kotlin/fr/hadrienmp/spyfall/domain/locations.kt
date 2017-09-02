package fr.hadrienmp.spyfall.domain

data class Location(val name: String)

interface Locations {
    fun random(): Location
}