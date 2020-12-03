import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("../inputs/DayOne").inputStream()
    val lineList = mutableListOf<Int>()
    inputStream.bufferedReader().forEachLine { lineList.add(it.toInt()) }

    lineList.forEach {
        val result = scanList(it, lineList)
        if (result != null) {
            println(result * it)
            return
        }
    }
}

fun scanList(currentItem: Int, list: List<Int>): Int? {
    list.forEach {
        if (currentItem + it == 2020) {
            return it
        }
    }

    return null
}
