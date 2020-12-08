package com.advent.dayeightfirst

import java.io.File

var accumulator = mutableListOf<Int>()
var path = mutableListOf<Int>()
var commands = listOf<Pair<String, Int>>()

fun main() {
    val fileInput = File("../inputs/DayEight").readText()

    commands = fileInput
    .split("\n")
    .map {
        val splitRes = it.split(' ')
        return@map Pair(splitRes[0], splitRes[1].toInt())
    }

    readNextCommand()
}

fun readNextCommand(index: Int = 0) {
    if (calculatePath(index)) return

    val cmd = commands[index].first
    val num = commands[index].second

    when (cmd) {
        "nop" -> readNextCommand(index + 1)
        "acc" -> {
            accumulator.add(num)
            readNextCommand(index + 1)
        }
        "jmp" -> readNextCommand(index + num)
    }
}

fun calculatePath(currentIndex: Int): Boolean {
    path.add(currentIndex)

    if (path.size < 2) {
        return false
    }

    if (path.groupingBy { it }.eachCount().containsValue(2)) {
        println(accumulator.sum())
        return true
    }

    return false
}
