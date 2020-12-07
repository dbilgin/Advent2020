package com.advent.daysevenfirst
import java.io.File

val myBag = "shiny gold"
fun main() {
    val fileInput = File("../inputs/DaySeven").readText()
    val bagList = fileInput
    .split("\n")
    .map {
        Regex("(?<=^)[a-z]+ [a-z]+(?= bag)|[a-z]+ [a-z]+(?= bag)")
        .findAllToList(it)
    }

    val result = getParentBags(bagList, myBag).map { it[0] }.distinct().size

    println(result)
}

fun getParentBags(bagList: List<List<String>>, bagToLook: String): MutableList<List<String>> {
    var filterResult: MutableList<List<String>> = bagList.filter { it.contains(bagToLook) && it[0] != bagToLook }.toMutableList()
    var iterator = filterResult.toMutableList()

    if (iterator.size != 0) {
        iterator.forEach {
            filterResult.addAll(getParentBags(bagList, it[0]))
        }
    }

    return filterResult
}

fun Regex.findAllToList(line: String): List<String> =
        this.findAll(line).map { matchResult -> matchResult.value }.toList()
