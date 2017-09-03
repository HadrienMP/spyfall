package fr.hadrienmp.spyfall.domain

class AlreadyRegistered : RuntimeException()

class NotEnoughPlayersException(numberOfPlayers: Int, minNumberOfPlayers: Int) :
        Exception("The game cannot be started with $numberOfPlayers players, " +
                "$minNumberOfPlayers is the minimum")

class UnknownPLayerException : RuntimeException()