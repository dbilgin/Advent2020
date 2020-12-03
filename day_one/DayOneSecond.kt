import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("../inputs/DayOne").inputStream()
    val lineList = mutableListOf<Int>()
    inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }

    lineList.forEach {
        val result = recursiveScan(it, lineList, Condition.SMALLER)
        if(result != null) {
            println(result * it)
            return
        }
    }
}

fun recursiveScan(currentItem: Int, list: List<Int>, condition: Condition): Int? {
    list.forEach {
        if (condition == Condition.EQUALS && currentItem + it == 2020) {
            return it
        } else if (condition == Condition.SMALLER && currentItem + it < 2020) {
            val recursiveResult = recursiveScan(currentItem + it, list, Condition.EQUALS)
            if(recursiveResult != null) return it * recursiveResult
        }
    }

    return null
}

enum class Condition {
  SMALLER, EQUALS
}
