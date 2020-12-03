import java.io.File
import java.io.InputStream
import java.math.BigInteger

fun main() {
    val inputStream: InputStream = File("../inputs/DayThree").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }

    val result = getTreeCount(lineList, 1, 1) *
        getTreeCount(lineList, 3, 1) *
        getTreeCount(lineList, 5, 1) *
        getTreeCount(lineList, 7, 1) *
        getTreeCount(lineList, 1, 2)

    println(result)
}

fun getTreeCount(lineList: List<String>, right: Int, down: Int): BigInteger {
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

  return treeTimes.toBigInteger()
}
