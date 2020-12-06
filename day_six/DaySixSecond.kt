package com.advent.daysixsecond

import java.io.File
import java.io.InputStream

fun main() {
    val fileInput = File("../inputs/DaySix").readText()
    val answers: List<List<String>> = fileInput
    .split("\n\n")
    .map { it.lines().filter { line -> line.isNotBlank() } }

    val result = answers.sumBy { group ->
        group
            .joinToString("")
            .groupingBy { it }
            .eachCount()
            .count { it.value == group.size }
    }

    println(result)
}