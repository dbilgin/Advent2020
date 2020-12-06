package com.advent.daysixfirst

import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("../inputs/DaySix").inputStream()

    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    var correctAnswers = mutableListOf<Int>()
    var currentGroup = ""

    lineList.forEachIndexed { index, element ->
        if (element.isNullOrEmpty() || index + 1 == lineList.size) {
            currentGroup += element

            val uniques = currentGroup.uniqueCharacters()
            correctAnswers.add(uniques.size)

            currentGroup = ""
            return@forEachIndexed
        } else {
            currentGroup += element
        }
    }

    println(correctAnswers.sum())
}

fun String.uniqueCharacters(): List<Char> = this.toCharArray().distinct()

