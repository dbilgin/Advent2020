import java.io.File
import java.io.InputStream

fun main() {
  val inputStream: InputStream = File("../inputs/DayTwo").inputStream()
  val lineList = mutableListOf<String>()
  inputStream.bufferedReader().forEachLine { lineList.add(it) }

  var count = 0
  lineList.forEach {
      val splitResult = it.split("-|\\: | ".toRegex())

      val firstIndex = splitResult[0].toInt() - 1
      val secondIndex = splitResult[1].toInt() - 1

      if (
        (splitResult[2].contains(splitResult[3][firstIndex]) && !splitResult[2].contains(splitResult[3][secondIndex])) ||
        (!splitResult[2].contains(splitResult[3][firstIndex]) && splitResult[2].contains(splitResult[3][secondIndex]))
      ) {
        count++
      }
  }

    println(count)
}
