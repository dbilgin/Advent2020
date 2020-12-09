package com.advent.dayninefirst

import java.io.File
import java.math.BigInteger

val preambleCount = 25
var numbers = listOf<BigInteger>()

fun main() {
    val fileInput = File("../inputs/DayNine").readText()

    numbers = fileInput
    .split("\n")
    .map(String::toBigInteger)

    val weaknessNum = checkWeakness()
    val sumNum = findSumNum(weaknessNum)
    println(sumNum)
}

fun findSumNum(sum: BigInteger, startingIndex: Int = 0): BigInteger? {
    var currentSum: BigInteger = 0.toBigInteger()

    val iterateList = numbers.slice(startingIndex..numbers.size - 1)

    iterateList.forEachIndexed { index, item ->
        if (currentSum < sum) {
            currentSum += item
        } else if (currentSum > sum) {
            return findSumNum(sum, startingIndex + 1)
        } else {
            val lastIndex = index - 1
            val resultList = iterateList.slice(0..lastIndex)
            return resultList.maxOrNull()!!.plus(resultList.minOrNull()!!)
        }
    }

    return null
}

fun checkWeakness(currentIndex: Int = preambleCount): BigInteger {
    val currentElement = numbers[currentIndex]
    val sumStartingIndex = currentIndex - preambleCount

    val iterateList = numbers.slice(sumStartingIndex..currentIndex - 1)

    var conditionMatched = false
    iterateList.forEach lit@{
        iterateList.forEach { item ->
            if (it != item && it + item == currentElement) {
                conditionMatched = true
                return@lit
            }
        }
    }

    if (conditionMatched) {
        return checkWeakness(currentIndex + 1)
    } else {
        return currentElement
    }
}
