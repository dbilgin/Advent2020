package com.advent.daytensecond

import java.io.File

fun main() {
    val fileInput = File("../inputs/DayTen").readText()

    val joltList = fileInput
    .split("\n")
    .map(String::toInt)
    .sorted()
    .toMutableList()

    println(getSequences(joltList))
}

fun getSequences(joltList: MutableList<Int>): Long {
    val reversedList = joltList
    .reversed()
    .toMutableList()
    reversedList.add(0)

    val limits = mutableMapOf(Pair(joltList.last()+3, 1L))

    return reversedList
    .fold(limits) { current, next ->
        current.apply {
            current[next] = listOfNotNull(current[next+1], current[next+2], current[next+3]).sum()
        }
    }
    .values
    .last()
}
