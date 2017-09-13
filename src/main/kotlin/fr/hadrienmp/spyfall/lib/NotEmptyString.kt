package fr.hadrienmp.spyfall.lib

data class NotEmptyString(val string: String) {
    init { require(string.trim() != "") { "Cannot be empty" } }
}
fun String.notEmpty(): NotEmptyString = NotEmptyString(this)