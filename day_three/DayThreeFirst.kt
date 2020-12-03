import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("../inputs/DayThree").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val right = 3
    val down = 1

    var currentXAxis = 0
    var treeTimes = 0

    lineList.forEachIndexed { index, element ->
        if (index % down != 0 || index == 0) {
            return@forEachIndexed
        }

        var currentElement = element
        currentXAxis += right

        while (currentXAxis + 1 > currentElement.length) {
            currentElement += currentElement
        }

        if (currentElement[currentXAxis] == '#') {
            treeTimes++
        }
    }

    println(treeTimes)
}
