package com.advent.dayeightsecond
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

    commands.forEachIndexed { index, item ->
        var listCopy = commands.toMutableList()
        accumulator = mutableListOf<Int>()
        path = mutableListOf<Int>()

        if (item.first == "jmp") listCopy[index] = Pair("nop", item.second)
        else if (item.first == "nop") listCopy[index] = Pair("jmp", item.second)

        val repeated = readNextCommand(listCopy)

        if (!repeated) {
            println(accumulator.sum())
            return
        }
    }
}

fun readNextCommand(list: List<Pair<String, Int>>, index: Int = 0): Boolean {
    if (calculatePath(index)) return true

    if (index > list.size - 1) return false

    val cmd = list[index].first
    val num = list[index].second

    when (cmd) {
        "nop" -> return readNextCommand(list, index + 1)
        "acc" -> {
            accumulator.add(num)
            return readNextCommand(list, index + 1)
        }
        "jmp" -> return readNextCommand(list, index + num)
    }

    return true
}

fun calculatePath(currentIndex: Int): Boolean {
    path.add(currentIndex)

    if (path.groupingBy { it }.eachCount().containsValue(2)) {
        return true
    }

    return false
}
