package com.advent.daysevensecond

import java.io.File

val myBag = "shiny gold"
var count = 0
fun main() {
    val fileInput = File("../inputs/DaySeven").readText()
    val bagList = fileInput
    .split("\n")
    .map {
        Regex("(?<=^)[a-z]+ [a-z]+(?= bag)|[1-9]+ [a-z]+ [a-z]+(?= bag)")
        .findAllToList(it)
    }

    getChildBags(bagList, myBag, 1)
    println(count)
}

fun getChildBags(bagList: List<List<String>>, bagToLook: String, multiplier: Int): MutableList<List<String>> {
    var filterResult: MutableList<List<String>> = bagList.filter { it.contains(bagToLook) && it[0] == bagToLook }.toMutableList()
    println(filterResult)

    var iterator = filterResult.toMutableList()

    if (iterator.size != 0) {
        iterator.forEach {
            it.forEachIndexed { index, item ->
                if (index == 0) {
                    return@forEachIndexed
                }

                var newItem = item.replace(("[^A-Za-z\\s.]").toRegex(), "").trim()
                var newBagCounts = item.replace(("[^\\d]").toRegex(), "").trim().toInt() * multiplier
                count = count + newBagCounts

                filterResult.addAll(getChildBags(bagList, newItem, newBagCounts))
             }
        }
    }

    return filterResult
}

fun Regex.findAllToList(line: String): List<String> =
        this.findAll(line).map { matchResult -> matchResult.value }.toList()
