package com.advent.daytenfirst

import java.io.File

fun main() {
    val fileInput = File("../inputs/DayTen").readText()

    val joltList = fileInput
    .split("\n")
    .map(String::toInt)
    .toMutableList()

    joltList.add(joltList.maxOrNull()!! + 3)
    joltList.sort()

    val result = joltList.listJolts() as List<Map<Int, Int>>

    val groupedResult = result.groupingBy { it.keys }.eachCount()
    val multipliedJoltDiffs = groupedResult.getDifferenceMultiplied(1, 3)
    println(multipliedJoltDiffs)
}

fun Map<Set<Int>, Int>.getDifferenceMultiplied(firstNum: Int, secondNum: Int): Int =
    this.filter { it.key.contains(firstNum) }.values.first() *
    this.filter { it.key.contains(secondNum) }.values.first()

fun MutableList<Int>.listJolts(currentJolt: Int = 0): MutableList<Map<Int, Int>>? {
    var sortedJolts = mutableListOf<Map<Int, Int>>()

    val maxNum: Int =  this.maxOrNull()!!
    if (currentJolt > maxNum) {
        return null
    }

    listOf(1, 2, 3).forEach lit@{
        if (this.contains(currentJolt + it)) {
            val newJolt = currentJolt + it
            sortedJolts.add(
                mapOf(Pair(it, newJolt))
            )
            val newList = this.listJolts(newJolt)

            if (newList != null) {
                sortedJolts.addAll(newList)
                return sortedJolts
            }

            return@lit
        }
    }

    return sortedJolts
}
